package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement


/**
 * Example of a Java-based migration.
 */
public class V7__add_status_to_flows implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Sql commands */
        List<String> commands = []

        /*
         * Add status column to flows table
         */
        commands += """
            ALTER TABLE flows ADD COLUMN status INT(11) NOT NULL;
        """

        /* Apply V7 commands */
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
