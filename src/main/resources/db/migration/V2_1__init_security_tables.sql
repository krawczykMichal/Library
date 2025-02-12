CREATE TABLE library_app_user
(
    user_id  SERIAL       NOT NULL,
    username varchar(40)  NOT NULL,
    email    VARCHAR(32)  NOT NULL,
    password VARCHAR(128) NOT NULL,
    active   BOOLEAN      NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE library_app_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE library_app_user_role
(
    id      serial not null,
    user_id INT    NOT NULL,
    role_id INT    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_library_app_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES library_app_user (user_id),
    CONSTRAINT fk_library_app_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES library_app_role (role_id)
);