
CREATE TABLE promotionResults (
    id BIGINT(11) AUTO_INCREMENT,

    promotion VARCHAR(8192) NOT NULL,
    status INT(11) NOT NULL,
    infoUrl TEXT,

    PRIMARY KEY (id)
);
