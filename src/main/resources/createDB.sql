DROP TABLE IF EXISTS user_feature CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS feature CASCADE;

CREATE TABLE users
(
    id          BIGSERIAL   NOT NULL UNIQUE
        CONSTRAINT users_pkey PRIMARY KEY,
    login       TEXT        NOT NULL UNIQUE,
    is_admin    BOOLEAN     NOT NULL DEFAULT false
);

CREATE TABLE feature
(
    id          BIGSERIAL   NOT NULL UNIQUE
        CONSTRAINT feature_pkey PRIMARY KEY,
    name        TEXT        NOT NULL UNIQUE
);

CREATE TABLE user_feature
(
    user_id     BIGSERIAL   NOT NULL,
    feature_id  BIGSERIAL   NOT NULL,
    PRIMARY KEY (user_id, feature_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),
    CONSTRAINT fk_feature
        FOREIGN KEY (feature_id)
            REFERENCES feature (id)
);