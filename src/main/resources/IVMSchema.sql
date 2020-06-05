create database if not exists ivm;
use ivm;

create table customers (
customer_id int auto_increment primary key,
customer_name varchar(25),
customer_fav_colour varchar(15)
);

describe customers;
select * from customers;


create table items (
item_id int auto_increment primary key,
item_name varchar(30),
item_price dec(7,2)
);

select * from items;
create table orders (
order_id int auto_increment primary key,
fk_customer_id int,
date_placed DATE,
total_price Decimal(7,2),
foreign key (fk_customer_id) references customers (customer_id)
);


create table orderline (
line_id int auto_increment primary key,
fk_order_id int,
fk_item_id int,
quantity_ordered int,
foreign key (fk_order_id) references orders (order_id),
foreign key (fk_item_id) references items (item_id)
);


select * from customers;