/*
 * Add some of the additional properties onto the artifacts table that were
 * missed in the V1 migration
 */

ALTER TABLE artifacts
    ADD COLUMN (
        /*
         * 255 characters of version information should be enough for anybody!
         */
        version VARCHAR(255),

        /*
         * The source URL could technically be pretty long, but we're also not
         * requiring it just yet
         */
        sourceUrl TEXT,

        /*
         * Timestamps for keeping track of data
         */
        createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
        deletedAt TIMESTAMP DEFAULT NULL
    );

CREATE INDEX version_index ON artifacts(version);
