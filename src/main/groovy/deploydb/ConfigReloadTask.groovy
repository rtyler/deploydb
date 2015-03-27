package deploydb

import com.codahale.metrics.annotation.Timed
import com.google.common.collect.ImmutableMultimap
import io.dropwizard.servlets.tasks.Task
import org.hibernate.Session
import org.hibernate.Transaction
import org.hibernate.context.internal.ManagedSessionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * Admin task to reload DeployDB configuration
 */
class ConfigReloadTask extends Task {
    private WorkFlow workFlow
    private static final Logger logger = LoggerFactory.getLogger(ConfigReloadTask.class)

    ConfigReloadTask(WorkFlow workFlow) {
        super('configReload')
        this.workFlow = workFlow
    }

    @Timed
    @Override
    void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {

        Session session = this.workFlow.deployDBApp.getSessionFactory().openSession()
        try {
            ManagedSessionContext.bind(session)
            Transaction transaction = session.beginTransaction()
            try {
                this.workFlow.loadConfigModels(true)
                transaction.commit()
                output.println("Done!")
                output.flush()
            }
            catch (Exception e) {
                transaction.rollback()
                logger.error("failed to reload the config: " + e.getMessage())
                output.println("Failed: Configuration reload is not allowed while deployments are in progress")
                output.flush()
            }
        } finally {
            session.close()
            ManagedSessionContext.unbind(this.workFlow.deployDBApp.getSessionFactory())
        }
    }
}