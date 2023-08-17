CREATE DATABASE inbalokesh_esakkipandi_corejava_project;

USE inbalokesh_esakkipandi_corejava_project;

CREATE TABLE users(
	id int auto_increment primary key NOT NULL,
    user_name varchar(100) NOT NULL,
    phone_number bigint unique NOT NULL,
    email varchar(100) unique NOT NULL,
    password varchar(20) NOT NULL,
    is_active boolean default(1),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE products(
	id int auto_increment primary key,
    name varchar(200) NOT NULL unique,
    type varchar(10) NOT NULL,
    quantity int NOT NULL,
    quantity_type varchar(15) NOT NULL,
    is_active boolean default(1) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_price(
	id int auto_increment primary key,
    product_id int,
    price int NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
	FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders(
	id int auto_increment primary key,
    quantity int default(1),
    total_price int NOT NULL,
    delivery_time enum('Breakfast', 'Lunch', 'Dinner'),
    order_status enum('Delivered', 'Not_delivered', 'Cancelled'),
    created_by int NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address varchar(300),
    FOREIGN KEY (created_by) REFERENCES users(id)
);
	
alter table orders
add product_id int,
add FOREIGN KEY (product_id) REFERENCES products(id);