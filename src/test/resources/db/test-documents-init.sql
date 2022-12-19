create table documents
(
    id           bigserial   not null,
    type         varchar(50) not null,
    number       varchar(50) not null,
    expiry_date  date,
    created_at   timestamp   not null,
    updated_at   timestamp   not null,
    passenger_id int8        not null,
    version      int8        not null,
    primary key (id)
);

insert into documents (type, "number", expiry_date, created_at, updated_at, version, passenger_id) values
       ('INTERNATIONAL_PASSPORT', '123', '2001-10-05', '2005-10-05', '2009-10-05', 1, 1),
       ('INTERNATIONAL_PASSPORT', '456', '2002-10-05', '2006-10-05', '2010-10-05', 1, 2),
       ('INTERNATIONAL_PASSPORT', '789', '2003-10-05', '2007-10-05', '2011-10-05', 1, 3),
       ('INTERNATIONAL_PASSPORT', '012', '2004-10-05', '2008-10-05', '2012-10-05', 1, 1);
