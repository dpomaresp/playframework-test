# auditinterview

## MYSQL Details

Host: vsinterview.ckdxitur80we.eu-west-1.rds.amazonaws.com

Username: vsuser123

Password: vspassword12345

DB: audit


## DATABASE Table

```SQL

CREATE TABLE event_by_user (
	id INT(11) AUTO_INCREMENT,
    tenant INT(11),
    user_id INT(11),
    event_time timestamp,
    operation varchar(255),
    user_role varchar(255),
    identifier INT(11),
    action varchar(255),
    entity varchar(255),
    entity_id INT(11),
    session_id varchar(255),
    details longtext,
    PRIMARY KEY (id)
);

```
