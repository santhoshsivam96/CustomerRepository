drop database if exists store_rating;
create database store_rating;
use  store_rating;



create table storetable (
   store_id int  NOT NULL AUTO_INCREMENT,
   store_name varchar(20),
  description varchar(5000),
  opening_hours varchar(20),
  overall_rating Double,
  total_rating int,
   constraint storetable_pk primary key (store_id)
);
insert into storetable (store_id, store_name, description, opening_hours,overall_rating,total_rating) values ("1001", 'Jos Alukas','It is a jewelry store', '12',4,3);

insert into storetable (store_id, store_name, description, opening_hours,overall_rating,total_rating) values ("1002", 'Saravana Bhavan','It is a authentic indian restaurant', '12',3.5,2);
commit;
select * from storetable;

create table usertable (
   user_id int not null,
   name varchar(20),
   rating int,
   sto_id int,
  CONSTRAINT rating_Ck CHECK (rating IN (1, 2, 3, 4,5)),
   constraint usertable_pk primary key (user_id),
   
   constraint fk_usertable_sto foreign key (sto_id) references storetable(store_id) 
);
insert into usertable (user_id, name, rating, sto_id) values (134, 'Martin',4, "1001");
insert into usertable (user_id, name, rating, sto_id) values (135, 'Mary',3,"1002");
insert into usertable (user_id, name, rating, sto_id) values (136, 'Hira',5,"1001");
insert into usertable (user_id, name, rating, sto_id) values (151, 'Salih',3,"1001");
insert into usertable (user_id, name, rating, sto_id) values (139, 'Sunny',4,"1002");
commit;
select * from usertable;