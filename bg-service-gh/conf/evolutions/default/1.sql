# --- First database schema

# --- !Ups

create table if not exists test.company
(
  id bigint auto_increment
    primary key,
  name varchar(255) not null
);

create table if not exists test.computer
(
  id bigint auto_increment,
  name varchar(255) not null,
  introduced timestamp null,
  discontinued timestamp null,
  company_id bigint null,
  constraint computer_pk
    primary key (id)
);

# --- !Downs

drop table if exists test.company;

drop table if exists test.computer;
