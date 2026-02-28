CREATE TABLE restaurant
(
    id             BIGINT PRIMARY KEY                DEFAULT 1 CHECK (id = 1),
    open_time      TIME WITH TIME ZONE      NOT NULL,
    close_time     time WITH TIME ZONE      NOT NULL,
    timings_step   INTEGER                  NOT NULL DEFAULT 15,
    max_book_hours INTEGER                  NOT NULL DEFAULT 3,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO restaurant (open_time, close_time)
VALUES ('10:00:00 +03:00', '22:00:00 +03:00');