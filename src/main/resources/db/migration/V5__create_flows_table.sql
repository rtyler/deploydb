CREATE TABLE flows (
    id BIGINT(11) AUTO_INCREMENT,

    artifactId BIGINT(11) NOT NULL,
    service TEXT NOT NULL,

    /*
     * Timestamps for keeping track of data
     */
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deletedAt TIMESTAMP DEFAULT NULL,


    PRIMARY KEY (id)
);

ALTER TABLE deployments ADD COLUMN flowId BIGINT(11);
ALTER TABLE deployments ADD FOREIGN KEY (flowId) REFERENCES flows(id);