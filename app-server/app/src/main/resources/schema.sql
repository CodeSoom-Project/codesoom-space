CREATE TABLE SEAT (
    id long NOT NULL auto_increment,
    number integer,
    isReserved boolean
);

CREATE TABLE SEAT_RESERVATION (
    id long NOT NULL,
    number integer,
    userName varchar(20),
    date varchar(20),
    checkIn varchar(20),
    checkOut varchar(20)
);

INSERT INTO SEAT (id, number, isReserved) VALUES (1, 1, 'false');
INSERT INTO SEAT (id, number, isReserved) VALUES (2, 2, 'false');
INSERT INTO SEAT (id, number, isReserved) VALUES (3, 3, 'false');
INSERT INTO SEAT (id, number, isReserved) VALUES (4, 4, 'false');
INSERT INTO SEAT (id, number, isReserved) VALUES (5, 5, 'false');
INSERT INTO SEAT (id, number, isReserved) VALUES (6, 6, 'false');
