/*
 * Alter the artifacts table to allow unique indexing on (group, name, version)
 */


ALTER TABLE artifacts ALTER COLUMN groupName VARCHAR(8192);
ALTER TABLE artifacts ALTER COLUMN name VARCHAR(8192);

/* No need for this index anymore */
DROP INDEX version_index;

CREATE UNIQUE INDEX artifact_version_index ON artifacts(groupName, name, version);

