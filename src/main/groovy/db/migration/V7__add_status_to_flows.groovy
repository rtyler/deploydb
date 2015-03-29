package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V7__add_status_to_flows extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 7
    }

    /**
     * Gather sql commands for this migration
     *
     * @param metadata
     * @return List of sql commands
     */
    List<String> prepareCommands(DatabaseMetaData metadata) {

        /* Sql commands */
        List<String> commands = []

        /*
         * Add status column to flows table
         */
        commands += """
            ALTER TABLE flows ADD COLUMN status INT NOT NULL;
        """

        return commands
    }
}
