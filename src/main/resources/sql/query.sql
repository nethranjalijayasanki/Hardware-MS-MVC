drop database if exists hardware;
    create database if not exists hardware;


use hardware;

create table users(
                     userId varchar(25),
                     password varchar(10)
);
insert into users values ('sanki','123');
create table customers(
                          c_id varchar(5) primary key,
                          name varchar(25) not null ,
                          address varchar(50) not null ,
                          tel varchar(10) not null ,
                          email varchar(50) not null
);

insert into customers values ("C001","Dinidu","Horana","0741255458","dinidu@gmail.com");
insert into customers values ("C002","sanki","Kaluthara","0741255558","sanki@gmail.com");

create table suppliers(
                          s_id varchar(5) primary key,
                          name varchar(25) not null ,
                          company varchar(50) not null ,
                          tel varchar(10) not null ,
                          email varchar(50) not null
);
insert into suppliers values ("S001","supun","sunil(plc)","0112457834","suniplc@gmail.com");
insert into suppliers values ("S002","amal","perera(plc)","0112667834","pereraplc@gmail.com");

create table items(
                      i_id varchar(5) primary key,
                      description varchar(25) not null ,
                      unit_price double not null ,
                      s_id varchar(5),
                      foreign key(s_id) references suppliers(s_id) on update cascade on delete cascade,
                      qty_on_hand int not null
);
insert into items values ("I001","paint",100.00,"S002",50);
insert into items values ("I002","spray gun",90.00,"S001",40);



create table drivers(
                        d_id varchar(5) primary key,
                        name varchar(25) not null ,
                        tel varchar(10) not null ,
                        email varchar(50) not null,
                        work_time varchar(50) not null
);
insert into drivers values ("D001","amil","0112227834","pereraplc@gmail.com","8h");
insert into drivers values ("D002","waruna","0116227834"","warunaplc@gmail.com","6h");


create table employees(
                          e_id varchar(5) primary key,
                          name varchar(20) not null ,
                          address varchar(20) not null ,
                          tel varchar(10) not null ,
                          email varchar(50) not null,
                          salary double not null ,
                          c_id varchar(5)  ,
                          foreign key(c_id) references customers(c_id) on update cascade on delete cascade
);
insert into employees values ("E001","kasun","colombo","0116227834","kasun@gmail.com",25000.00,"C001");


create table transports(
                           t_id varchar(5) primary key,
                           description varchar(50) not null ,
                           d_id varchar(5) ,
                           foreign key(d_id) references drivers(d_id) on update cascade on delete cascade
);


create table orders(
                       o_id varchar(5) primary key,
                       name varchar(20) not null ,
                       qty_on_hand int not null ,
                       price double not null ,
                       c_id varchar(5),
                       foreign key(c_id) references customers(c_id) on update cascade on delete cascade,
                       s_id varchar(5),
                       foreign key(s_id) references suppliers(s_id) on update cascade on delete cascade
);
/*
create table payments(A
                         p_id varchar(5) primary key,
                         discount int not null ,
                         total_price double not null ,
                         date int not null ,
                         o_id varchar(5),
                         foreign key(o_id) references orders(o_id) on update cascade on delete cascade
);

 */

create table order_item_detail(
                                  o_qty_on_hand int not null ,
                                  sold_item_count int not null ,
                                  date date ,
                                  unit_price double not null,
                                  o_id varchar(5),
                                  foreign key(o_id) references orders(o_id) on update cascade on delete cascade,
                                  i_id varchar(5),
                                  foreign key(i_id) references items(i_id) on update cascade on delete cascade
);
/*
create table supplier_item_detail(
                                     o_purchase_price double not null ,
                                     o_purchase_item_count int not null ,
                                     o_date int not null ,
                                     s_id varchar(5),
                                     foreign key(s_id) references suppliers(s_id) on update cascade on delete cascade,
                                     i_id varchar(5),
                                     foreign key(i_id) references items(i_id) on update cascade on delete cascade
);

 */

