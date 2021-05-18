create database airport;

create table plane (
    plane_model text not null,
    serial_number text primary key,
    number_of_seats int not null
    );

create table pilots (
    name text not null,
    age int not null,
    plane_model text not null
);

insert into plane (plane_model, serial_number, number_of_seats) values ('Boeing 747', 'aboba1', 467),
                                                                        ('Boeing 747', 'aboba2', 467),
                                                                        ('Airbus A320', 'aboba3', 186),
                                                                        ('Airbus A320', 'aboba4', 186),
                                                                        ('Airbus A320', 'aboba5', 186),
                                                                        ('Airbus A320', 'aboba6', 186),
                                                                        ('Airbus A320', 'aboba7', 186);

insert into pilots (name, age, plane_model) values ('Vlad', 54, 'Boeing 747'),
                                      ('Vlad', 54, 'Airbus A320'),
                                     ('Ihor', 33, 'Boeing 747'),
                                      ('Ihor', 33, 'Airbus A320'),
                                     ('Mike', 37, 'Boeing 747'),
                                     ('Ivan', 30, 'Airbus A320'),
                                     ('Kate', 43, 'Airbus A320'),
                                     ('Sasha', 34, 'Airbus A320'),
                                     ('Misha', 29, 'Airbus A320'),
                                     ('Artur', 49, 'Airbus A320');
