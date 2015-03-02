ALTER TABLE deployments ADD COLUMN flowId BIGINT(11);
ALTER TABLE deployments ADD FOREIGN KEY (flowId) REFERENCES flows(id);