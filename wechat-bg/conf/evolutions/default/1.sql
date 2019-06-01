# --- !Ups

create table if not exists scm.student
(
  id                   bigint auto_increment,
  name                 varchar(255)                        not null,
  gender               varchar(32)                         not null,
  class                varchar(255)                        not null,
  id_no                varchar(9)                          not null,
  birthday             varchar(32)                         not null,
  serial_no            varchar(9)                          not null,
  native_place         varchar(255)                        not null,
  registered_residence varchar(255)                        not null,
  address              varchar(255)                        not null,
  modify_time          timestamp default CURRENT_TIMESTAMP not null,
  create_time          timestamp default CURRENT_TIMESTAMP not null,
  constraint course_pk
    primary key (id)
);

# --- !Downs

drop table if exists scm.student;
