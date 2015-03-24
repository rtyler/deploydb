package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V3__artifacts_unique_index extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 3
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

        /* Change groupName data type*/
        if (isH2(metadata.driverName)) {
            commands += """
                ALTER TABLE artifacts ALTER COLUMN groupName VARCHAR(8192);
            """
        } else {
            commands += """
                ALTER TABLE artifacts MODIFY COLUMN groupName VARCHAR(8192);
            """
        }

        /* Change name data type */
        if (isH2(metadata.driverName)) {
            commands += """
                ALTER TABLE artifacts ALTER COLUMN name VARCHAR(8192);
            """
        } else {
            commands += """
                ALTER TABLE artifacts MODIFY COLUMN name VARCHAR(8192);
            """
        }

        /* No need for this index anymore */
        if (isH2(metadata.driverName)) {
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
        if (isH2(metadata.driverName)) {
            commands += """
                CREATE UNIQUE INDEX artifact_version_index ON artifacts(groupName, name, version);
            """
        } else {
            commands += """
                CREATE UNIQUE INDEX artifactveridx ON artifacts (groupName(50), name(50), version(50));
            """
        }

        return commands
    }
}
