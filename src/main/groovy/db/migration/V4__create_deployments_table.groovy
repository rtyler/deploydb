package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement


/**
 * Example of a Java-based migration.
 */
public class V4__create_deployments_table implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Sql commands */
        List<String> commands = []

        /*
         * Create deployments table
         */
        commands += """
            CREATE TABLE deployments (
                id BIGINT(11) AUTO_INCREMENT,

                artifactId BIGINT(11) NOT NULL,
                environment VARCHAR(8192) NOT NULL,
                status INT(11) NOT NULL,

                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                deletedAt TIMESTAMP DEFAULT NULL,

                PRIMARY KEY (id)
            );
        """

        /* Create index on deployments table */
        commands += """
            CREATE INDEX deploys_by_artifact ON deployments(artifactId);
        """

        /* Apply V4 commands */
        for (String command : commands) {
            PreparedStatement statement = connection.prepareStatement(command)
            try {
                statement.execute()
            } finally {
                statement.close()
            }
        }
    }
}



