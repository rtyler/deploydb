package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V1__create_artifacts_table extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 1
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
         * Add artifacts table
         */
        commands += """
            CREATE TABLE artifacts (
                id BIGINT(11) AUTO_INCREMENT,

                groupName TEXT NOT NULL,

                name TEXT NOT NULL,

                PRIMARY KEY (id)
            );
        """

        return commands
    }
}
