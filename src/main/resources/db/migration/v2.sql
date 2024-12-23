ALTER TABLE msc_user
    MODIFY dtype VARCHAR (31) NULL;

ALTER TABLE msc_user
    MODIFY help_requests INT NOT NULL;

ALTER TABLE st_user
    MODIFY help_requests INT NOT NULL;

ALTER TABLE st_user
    MODIFY user_type INT NULL;