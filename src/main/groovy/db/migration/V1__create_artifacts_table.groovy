package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement


/**
 * Example of a Java-based migration.
 */
public class V1__create_artifacts_table implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Sql commands */
        List<String> commands = []

        /*
         * This migration creates some of the basic initial data structures
         */
        commands += """
            CREATE TABLE artifacts (
                id BIGINT(11) AUTO_INCREMENT,

                groupName TEXT NOT NULL,

                name TEXT NOT NULL,

                PRIMARY KEY (id)
            );
        """

        /* Apply V1 commands */
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
