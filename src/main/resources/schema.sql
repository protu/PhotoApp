create table if not exists users (
  username varchar(20) not null primary key,
  password varchar(100) not null,
  enabled boolean not null
);

create table if not exists authorities (
  username varchar(20) not null,
  authority varchar(20) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);

create table if not exists pictures (
    id identity,
    name varchar (20) not null,
    path varchar (128) not null,
    description varchar (128) not null,
    username varchar (20) not null,
      constraint fk_users foreign key(username) references users(username)

);

create table if not exists hashtags(
    id identity,
    name varchar (50)
);

create table if not exists hash2pict (
    picture long not null,
    hashtag long not null,
    constraint fk_picture foreign key(picture) references pictures(id),
    constraint fk_hashtag foreign key(hashtag) references hashtags(id)
);