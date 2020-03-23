INSERT INTO categories(id, name) VALUES (1, 'Книги');

INSERT INTO customers(id, email, password) VALUES (1, 'test@mail.com', '12345678');

INSERT INTO products(id, measure, name, price, stock) VALUES (1, 'штук', 'Война и Мир', 1000.0, 5);
INSERT INTO products(id, measure, name, price, stock) VALUES (2, 'штук', 'Spring in Action', 1500.0, 10);

INSERT INTO product_category(category_id, product_id) VALUES (1, 1);
INSERT INTO product_category(category_id, product_id) VALUES (1, 2);

INSERT INTO carts(id, total_price, customer_id) VALUES (1, 1000.0, 1);
INSERT INTO carts(id, total_price, customer_id) VALUES (2, 2500.0, 1);

INSERT INTO cart_items(id, amount, product_id) VALUES (1, 1, 1);
INSERT INTO cart_items(id, amount, product_id) VALUES (2, 1, 1);
INSERT INTO cart_items(id, amount, product_id) VALUES (3, 1, 2);

INSERT INTO carts_cart_items(cart_id, cart_items_id) VALUES (1, 1);
INSERT INTO carts_cart_items(cart_id, cart_items_id) VALUES (2, 2);
INSERT INTO carts_cart_items(cart_id, cart_items_id) VALUES (2, 3);

