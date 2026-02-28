INSERT INTO tables (guests, x, y, is_vertical)
VALUES (2, 76, 174, FALSE),
       (2, 215, 174, FALSE),
       (2, 17, 211, TRUE),
       (2, 17, 308, TRUE),
       (2, 105, 350, FALSE),
       (2, 205, 350, FALSE),
       (4, 105, 250, FALSE),
       (4, 205, 250, FALSE),
       (4, 315, 250, FALSE),
       (4, 425, 250, FALSE),
       (6, 370, 360, FALSE),
       (6, 370, 500, FALSE),
       (2, 285, 425, TRUE),
       (2, 285, 525, TRUE),
       (2, 40, 436, TRUE),
       (2, 168, 436, TRUE),
       (2, 40, 564, TRUE),
       (2, 168, 564, TRUE);

INSERT INTO table_types (type, title)
VALUES ('NEAR_WINDOW', 'Near the window'),
       ('VIP', 'VIP tables'),
       ('GROUP', 'Tables for big groups'),
       ('ON_TERRACE', 'Table on a terrace');

INSERT INTO tables_table_types (table_id, table_type_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (15, 2),
       (16, 2),
       (17, 2),
       (18, 2);