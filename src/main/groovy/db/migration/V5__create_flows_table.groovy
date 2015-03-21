package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement


/**
 * Example of a Java-based migration.
 */
public class V5__create_flows_table implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Sql commands */
        List<String> commands = []

        /*
         * Create flows table
         */
        commands += """
            CREATE TABLE flows (
                id BIGINT(11) AUTO_INCREMENT,

                artifactId BIGINT(11) NOT NULL,
                service TEXT NOT NULL,

                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                deletedAt TIMESTAMP DEFAULT NULL,

                PRIMARY KEY (id)
            );
        """

        /* Add flowId to deployments table */
        commands += """
            ALTER TABLE deployments ADD COLUMN flowId BIGINT(11);
        """

        /* Add foreign key */
        commands += """
            ALTER TABLE deployments ADD FOREIGN KEY (flowId) REFERENCES flows(id);
        """

        /* Apply V5 commands */
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


