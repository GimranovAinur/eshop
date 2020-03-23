create table cart_items (id int8 not null, amount integer default '1', product_id int8, primary key (id));
create table carts (id int8 not null, total_price float8, customer_id int8, primary key (id));
create table carts_cart_items (cart_id int8 not null, cart_items_id int8 not null);
create table categories (id int4 not null, name varchar(255), primary key (id));
create table customers (id int8 not null, email varchar(255), password varchar(255), primary key (id));
create table product_category (category_id int4, product_id int8 not null, primary key (product_id));
create table products (id int8 not null, measure varchar(255), name varchar(255), price float8, stock int4, primary key (id));

alter table if exists carts_cart_items add constraint UK_cart_item_id unique (cart_items_id);
alter table if exists cart_items add constraint FK_cart_item_product foreign key (product_id) references products;
alter table if exists carts add constraint FK_cart_customer foreign key (customer_id) references customers;
alter table if exists carts_cart_items add constraint FK_cart_item foreign key (cart_items_id) references cart_items;
alter table if exists carts_cart_items add constraint FK_cart foreign key (cart_id) references carts;
alter table if exists product_category add constraint FK_product_category foreign key (category_id) references categories;
alter table if exists product_category add constraint FK5w81wp3eyugvi2lii94iao3fm foreign key (product_id) references products;