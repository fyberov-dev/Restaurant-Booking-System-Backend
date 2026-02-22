CREATE TABLE tables
(
    id         BIGSERIAL PRIMARY KEY,
    guests     INTEGER                  NOT NULL CHECK (guests > 0),
    x          FLOAT                    NOT NULL CHECK (x > 0),
    y          FLOAT                    NOT NULL CHECK (y > 0),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);