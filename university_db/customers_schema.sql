DROP TABLE IF EXISTS customers;

CREATE TABLE customers(
    CID text,
    Company text,
    City text,
    Amount numeric(12,2)
);

insert into customers values ('001', 'Sazons', 'Cullowhee', 25.0);
insert into customers values ('002', 'Mad Batter', 'Sylva', 30.0);
insert into customers values ('003', 'City Lights Bookstore', 'Sylva', 28.0);
