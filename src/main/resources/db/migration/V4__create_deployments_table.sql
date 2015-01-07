
CREATE TABLE deployments (
    id BIGINT(11) AUTO_INCREMENT,

    artifactId BIGINT(11) NOT NULL,
    environment VARCHAR(8192) NOT NULL,
    status INT(11) NOT NULL,


    /*
     * Timestamps for keeping track of data
     */
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deletedAt TIMESTAMP DEFAULT NULL,

    PRIMARY KEY (id)
);

CREATE INDEX deploys_by_artifact ON deployments(artifactId);
