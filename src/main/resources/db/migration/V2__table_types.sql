CREATE TABLE table_types
(
    id         BIGSERIAL PRIMARY KEY,
    type       TEXT                     NOT NULL,
    title      TEXT                     NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);