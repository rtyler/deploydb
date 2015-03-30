package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V2__artifacts_versions extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 2
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
                    deletedAt TIMESTAMP NULL
                );
        """

        /**
         * Create new version_index
         */
        commands += """
            CREATE INDEX version_index ON artifacts(version);
        """

        return commands
    }
}

