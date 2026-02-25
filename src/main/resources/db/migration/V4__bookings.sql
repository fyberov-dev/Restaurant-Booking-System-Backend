CREATE TABLE bookings
(
    id         BIGSERIAL PRIMARY KEY,
    table_id   BIGINT                   REFERENCES tables (id) ON DELETE SET NULL,
    phone      TEXT                     NOT NULL,
    email      TEXT                     NOT NULL,
    starts_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    ends_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bookings_start_at ON bookings (starts_at);