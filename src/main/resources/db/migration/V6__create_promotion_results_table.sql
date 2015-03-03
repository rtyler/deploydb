CREATE TABLE promotionResults (
    id BIGINT(11) AUTO_INCREMENT,

    promotion VARCHAR(8192) NOT NULL,
    status INT(11) NOT NULL,
    infoUrl TEXT,
    deploymentId BIGINT(11) NOT NULL,

    /*
     * Timestamps for keeping track of data
     */
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deletedAt TIMESTAMP DEFAULT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (deploymentId) REFERENCES deployments(id)
);

ALTER TABLE deployments ADD COLUMN service VARCHAR(8192) NOT NULL;
