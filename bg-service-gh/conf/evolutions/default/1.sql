# --- !Ups

create table if not exists scm.course
(
  id          bigint auto_increment,
  title       varchar(255) not null,
  create_time timestamp    null,
  constraint course_pk
    primary key (id)
);

# --- !Downs

drop table if exists scm.course;
