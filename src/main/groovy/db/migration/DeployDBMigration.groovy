package db.migration

import groovy.transform.TypeChecked
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.DatabaseMetaData
import org.flywaydb.core.api.migration.jdbc.JdbcMigration
import org.flywaydb.core.api.migration.MigrationChecksumProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@TypeChecked
abstract class DeployDBMigration implements JdbcMigration, MigrationChecksumProvider {
    protected Logger logger = LoggerFactory.getLogger(DeployDBMigration.class)

    /** Implementing classes responsible for providing a list of String commands */
    abstract List<String> prepareCommands(DatabaseMetaData metadata)

    @Override
    void migrate(Connection connection) {
        executeCommands(connection, prepareCommands(connection.metaData))
    }

    /** Tell me if I'm executing this migration on this driver */
    Boolean isH2(String driverName) {
        return driverName.matches("(?i).*H2.*")
    }

    /** Execute the commands after preparing all the statements */
    private void executeCommands(Connection connection, List<String> commands) {
        commands.each { String command ->
            logger.info("Preparing statement for: ${command}")
            PreparedStatement statement = connection.prepareStatement(command)

            try {
                statement.execute()
            } finally {
                statement.close()
            }
        }
    }
}