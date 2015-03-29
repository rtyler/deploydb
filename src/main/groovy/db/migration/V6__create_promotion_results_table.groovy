package db.migration

import java.sql.DatabaseMetaData


/**
 * Example of a Java-based migration.
 */
class V6__create_promotion_results_table extends DeployDBMigration {

    /** Return migration number to differentiate from other versions */
    @Override
    Integer getChecksum() {
        return 6
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
         * Create promotionResults table
         */
        commands += """
            CREATE TABLE promotionResults (
                id BIGINT(11) AUTO_INCREMENT,

                promotion VARCHAR(8192) NOT NULL,
                status INT(11) NOT NULL,
                infoUrl TEXT,
                deploymentId BIGINT(11) NOT NULL,

                createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                deletedAt TIMESTAMP NULL,

                PRIMARY KEY (id),
                FOREIGN KEY (deploymentId) REFERENCES deployments(id)
            );
        """

        /* Create index on deployments table */
        commands += """
            ALTER TABLE deployments ADD COLUMN service VARCHAR(8192) NOT NULL;
        """

        return commands
    }
}
