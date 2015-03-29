package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V5__create_flows_table extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 5
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
         * Create flows table
         */
        commands += """
            CREATE TABLE flows (
                id BIGINT(11) AUTO_INCREMENT,

                artifactId BIGINT(11) NOT NULL,
                service TEXT NOT NULL,

                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                deletedAt TIMESTAMP NULL,

                PRIMARY KEY (id)
            );
        """

        /* Add flowId to deployments table */
        commands += """
            ALTER TABLE deployments ADD COLUMN flowId BIGINT(11);
        """

        /* Add foreign key */
        commands += """
            ALTER TABLE deployments ADD FOREIGN KEY (flowId) REFERENCES flows(id);
        """

        return commands
    }
}


