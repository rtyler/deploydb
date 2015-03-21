package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement


/**
 * Example of a Java-based migration.
 */
public class V6__create_promotion_results_table implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Sql commands */
        List<String> commands = []

        /*
         * Create promotionResults table
         */
        commands += """
            CREATE TABLE promotionResults (
                id BIGINT(11) AUTO_INCREMENT,

                promotion VARCHAR(8192) NOT NULL,
                status INT(11) NOT NULL,
                infoUrl TEXT,
                deploymentId BIGINT(11) NOT NULL,

                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                deletedAt TIMESTAMP DEFAULT NULL,

                PRIMARY KEY (id),
                FOREIGN KEY (deploymentId) REFERENCES deployments(id)
            );
        """

        /* Create index on deployments table */
        commands += """
            ALTER TABLE deployments ADD COLUMN service VARCHAR(8192) NOT NULL;
        """

        /* Apply V6 commands */
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
