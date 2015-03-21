package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement


/**
 * Example of a Java-based migration.
 */
public class V2__artifacts_versions implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Sql commands */
        List<String> commands = []

        /**
         * Add new columns
         * - 255 characters of version information should be enough for anybody!
         * - The source URL could technically be pretty long, but we're also not
         *   requiring it just yet
         * - Timestamps for keeping track of data
         */
        commands += """
            ALTER TABLE artifacts
                ADD COLUMN (
                    version VARCHAR(255) NOT NULL,
                    sourceUrl TEXT,
                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                    deletedAt TIMESTAMP DEFAULT NULL
                );
        """

        /**
         * Create new version_index
         */
        commands += """
            CREATE INDEX version_index ON artifacts(version);
        """

        /* Apply V2 commands */
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

