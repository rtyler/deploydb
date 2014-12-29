/*
 * This migration is creates some of the basic initial data structures
 */

CREATE TABLE artifacts (
    id BIGINT(11) AUTO_INCREMENT,

    groupName TEXT NOT NULL,

    name TEXT NOT NULL,

    PRIMARY KEY (id)
);
