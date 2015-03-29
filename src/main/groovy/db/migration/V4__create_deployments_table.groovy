package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V4__create_deployments_table extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 4
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
         * Create deployments table
         */
        commands += """
            CREATE TABLE deployments (
                id BIGINT AUTO_INCREMENT,

                artifactId BIGINT NOT NULL,
                environment VARCHAR(8192) NOT NULL,
                status INT NOT NULL,

                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                deletedAt TIMESTAMP NULL,

                PRIMARY KEY (id)
            );
        """

        /* Create index on deployments table */
        commands += """
            CREATE INDEX deploys_by_artifact ON deployments(artifactId);
        """

        return commands
    }
}



