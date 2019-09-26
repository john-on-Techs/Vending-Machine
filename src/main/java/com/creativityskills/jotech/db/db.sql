create table cash_drawer
(
    id           int         not null auto_increment primary key,
    denomination varchar(50) not null,
    dn_count     bigint      not null
);
create table product
(
    id         int          not null auto_increment primary key,
    name       varchar(100) not null,
    unit_price DECIMAL      not null
);
create table stock
(
    id       int    not null auto_increment primary key,
    product  int    not null,
    quantity bigint not null,
    foreign key (product) references product (id) on delete cascade on update cascade
);

create table sale
(
    id       int     not null auto_increment primary key,
    date     date    not null,
    product  int     not null,
    quantity bigint  not null,
    amount   decimal not null,
    foreign key (product) references product (id) on delete cascade on update cascade
);