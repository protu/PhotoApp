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