CREATE TABLE tables_table_types
(
    table_id      BIGINT NOT NULL REFERENCES tables (id) ON DELETE CASCADE,
    table_type_id BIGINT NOT NULL REFERENCES table_types (id) ON DELETE CASCADE,
    PRIMARY KEY (table_id, table_type_id)
);