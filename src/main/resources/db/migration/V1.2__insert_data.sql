INSERT INTO categories(id, name) VALUES (1, 'Книги');

INSERT INTO users(id, email, password) VALUES (1, 'test@mail.com', '12345678');

INSERT INTO products(id, name, price) VALUES (1,  'Война и Мир', 1000.0);
INSERT INTO products(id, name, price) VALUES (2,  'Spring in Action', 1500.0);

INSERT INTO warehouse(id, measure, stock, product_id) VALUES (1, 'штук', 5, 1);
INSERT INTO warehouse(id, measure, stock, product_id) VALUES (2, 'штук', 10, 2);

INSERT INTO product_category(category_id, product_id) VALUES (1, 1);
INSERT INTO product_category(category_id, product_id) VALUES (1, 2);

INSERT INTO carts(id, total_price, user_id) VALUES (1, 1000.0, 1);
INSERT INTO carts(id, total_price, user_id) VALUES (2, 2500.0, 1);

INSERT INTO cart_items(id, amount, product_id, cart_id) VALUES (1, 1, 1, 1);
INSERT INTO cart_items(id, amount, product_id, cart_id) VALUES (2, 1, 1, 2);
INSERT INTO cart_items(id, amount, product_id, cart_id) VALUES (3, 1, 2, 2);

