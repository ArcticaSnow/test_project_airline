create TABLE passengers (
    id bigserial NOT NULL,
    email varchar NULL,
    "password" varchar NULL,
    first_name varchar NOT NULL,
    middle_name varchar NULL,
    last_name varchar NOT NULL,
    birthday date NOT NULL,
    mobile_num varchar NULL,
    nick_name varchar NULL,
    CONSTRAINT passengers_id_key UNIQUE (id),
    CONSTRAINT passengers_pk PRIMARY KEY (id)
    );

insert into passengers (email,"password",first_name,middle_name,last_name,birthday,mobile_num,nick_name) values
	 ('admin@mail.ru','$2a$12$uihsiP1OnAtsNyYy/uySxeuaGcl9yFmFsujSn8MSwivpTUx0f5Ecm','Admin','Adminovich','Adminov','2000-08-01','+7 916 123-45-67','@admin'),
	 ('manager@mail.ru','$2a$12$/bvCLyjsmrqwgme39yQcMOFn7y/MwrqYjwFotk4b/BY/OJebXOtpu','Manager',NULL,'Managerov','1991-10-13','+7 910 123-45-67','@manager'),
	 ('passenger@mail.ru','$2a$12$/UQ7grshQafykFQa3wpdhuqM3ZxiNBYyiprwF9eQDSYHIQIBoPm22','Passenger','Passengerovich','Pass','2000-10-05','+7 916 123-45-56','@passenger'),
	 ('del@mail.ru','$2a$12$G2EAzTVm7VZ4dN0r/Llh.e.aL8sek0T0ek5ca63sIzi28G17B1gd2','Delita','Delitovna','Deletova','1992-10-10','+7 905 123-78-90','@del'),
	 ('dela@mail.ru','$2a$12$G2EAzTVm7VZ4dN0r/Llh.e.aL8sek0T0ek5ca63sIzi28G17B1gd2','Dela','Delitovna','Deletova','1992-10-10','+7 905 100-00-00',NULL);
