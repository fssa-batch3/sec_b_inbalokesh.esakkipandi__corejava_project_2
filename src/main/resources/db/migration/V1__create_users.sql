CREATE DATABASE only_home_food;

USE only_home_food;

CREATE TABLE users(
	id int auto_increment primary key,
    user_name varchar(100) NOT NULL,
    phone_number int unique NOT NULL,
    email varchar(100) unique NOT NULL,
    password varchar(20) NOT NULL,
    is_active boolean default(1),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE products(
	product_id int auto_increment primary key,
    food_name varchar(200) NOT NULL,
    food_type varchar(10) NOT NULL,
    description varchar(100),
    is_active boolean default(1) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_price(
	price_id int auto_increment primary key,
    product_id int,
    price int NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
	FOREIGN KEY (product_id) REFERENCES products(product_id)
);

alter table products
add price_id int,
add foreign key (price_id) REFERENCES product_price(price_id);

CREATE TABLE orders(
	order_id int auto_increment primary key,
    quantity int default(1),
    total_price int NOT NULL,
    delivery_time enum('Breakfast', 'Lunch', 'Dinner'),
    order_status enum('Delivered', 'Not-delivered', 'Cancelled'),
    created_by int NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address varchar(300),
    FOREIGN KEY (created_by) REFERENCES users(id)
);

alter table orders
add product_id int,
add FOREIGN KEY (product_id) REFERENCES products(product_id);


insert into users (user_name, phone_number, email, password)
Values("Lokesh", "8899889988", "loki@gmail.com", "54321");

insert into products (food_name, food_type, description)
Values("Idly", "Veg", "4 Idly with Sambar and chutney");

insert into product_price (product_id, price)
Values(3, 19);

insert into orders (quantity, total_price, delivery_time, order_status, created_by, address, product_id)
Values(3, 57, 'Breakfast', "Not-delivered", 2, "No.1 merku theru uthandi Chennai", 3);

update product_price
set start_date = '2023-08-03 23:06:26'
where price_id = 2;