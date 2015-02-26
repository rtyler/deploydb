/*
 * Add some of the additional properties onto the deployments table that were
 * missed in the V1 migration
 */

ALTER TABLE deployments
    ADD COLUMN (
        /*
         * Service Identifier
         */
        service VARCHAR(8192) NOT NULL
    );

