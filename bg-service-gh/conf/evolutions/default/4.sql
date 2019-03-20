# --- !Ups

create table if not exists test.user
(
  id          bigint auto_increment,
  telephone   varchar(64)                         not null,
  nick_name   varchar(255)                        null,
  password    varchar(255)                        not null,
  modify_time timestamp default CURRENT_TIMESTAMP not null,
  create_time timestamp default CURRENT_TIMESTAMP not null,
  constraint user_pk
    primary key (id)
);

create unique index user_telephone_uindex
  on user (telephone);

# --- !Downs

drop table if exists test.user;
