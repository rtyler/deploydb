package db.migration

import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
public class V3__artifacts_unique_index implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {

        /* Get db meta data, to fetch driver name and customize the sql command */
        DatabaseMetaData dbMetaData = connection.getMetaData()

        /* Sql commands */
        List<String> commands = []

        /* Change groupName data type*/
        if (dbMetaData.getDriverName().toLowerCase().contains("h2")) {
            commands += """
                ALTER TABLE artifacts ALTER COLUMN groupName VARCHAR(8192);
            """
        } else {
            commands += """
                ALTER TABLE artifacts MODIFY COLUMN groupName VARCHAR(8192);
            """
        }

        /* Change name data type */
        if (dbMetaData.getDriverName().toLowerCase().contains("h2")) {
            commands += """
                ALTER TABLE artifacts ALTER COLUMN name VARCHAR(8192);
            """
        } else {
            commands += """
                ALTER TABLE artifacts MODIFY COLUMN name VARCHAR(8192);
            """
        }

        /* No need for this index anymore */
        if (dbMetaData.getDriverName().toLowerCase().contains("h2")) {
            commands += """
                DROP INDEX version_index;
            """
        } else {
            commands += """
                DROP INDEX version_index ON artifacts;
            """
        }

        /**
         * Create unique index.
         *
         * For non-h2 databases, limit size of the key to 150 characters. In general,
         * It has to be less than max-allowed/3; max-allowed for mysql is 767.
         **/
        if (dbMetaData.getDriverName().toLowerCase().contains("h2")) {
            commands += """
                CREATE UNIQUE INDEX artifact_version_index ON artifacts(groupName, name, version);
            """
        } else {
            commands += """
                CREATE UNIQUE INDEX artifactveridx ON artifacts (groupName(50), name(50), version(50));
            """
        }

        /* Apply V3 commands */
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
