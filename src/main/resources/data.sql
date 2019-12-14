insert into users (username, password, enabled)
values ('admin', '$2y$12$7FdiTM6dXrnnRm9tjZaSpezpw5WncO.ZGSVMPQKOHLOs3hlWpBSrW', 1);
insert into users (username, password, enabled)
values ('luser', '$2y$12$jSMWmnPBVkssQ5Z2GEs/b.0VhyIllxlpA6UNMtqIDEno.8BYy14Cy', 1);

insert into authorities (username, authority)
values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority)
values ('admin', 'ROLE_USER');
insert into authorities (username, authority)
values ('luser', 'ROLE_USER');

insert into pictures (name, path, description, username)
values ('Train', 'pict/pict001.jpg', 'Train is comming', 'admin');
insert into pictures (name, path, description, username)
values ('MDF', 'pict/pict002.jpg', 'Main distribution frame', 'admin');
insert into pictures (name, path, description, username)
values ('Sport car', 'pict/pict003.jpg', 'Ferrari 348', 'admin');
insert into pictures (name, path, description, username)
values ('Fruit', 'pict/pict005.jpg', 'Fruit basket', 'admin');
insert into pictures (name, path, description, username)
values ('Marina', 'pict/pict006.jpg', 'Boats in marina', 'admin');
insert into pictures (name, path, description, username)
values ('Fox', 'pict/pict007.jpg', 'Fox in the grass', 'admin');
insert into pictures (name, path, description, username)
values ('Snow', 'pict/pict008.jpg', 'Snow mountain', 'admin');
insert into pictures (name, path, description, username)
values ('Puppy', 'pict/pict004.jpg', 'Puppy', 'admin');
insert into pictures (name, path, description, username)
values ('City', 'pict/pict009.jpg', 'City line', 'admin');
insert into pictures (name, path, description, username)
values ('Forest', 'pict/pict010.jpg', 'Burned forest', 'admin');

-- insert into hashtags(name)
-- values ('animals');
--
-- insert into hash2pict(picture, hashtag)
-- values (4, 1);
-- insert into hash2pict(picture, hashtag)
-- values (7, 1);