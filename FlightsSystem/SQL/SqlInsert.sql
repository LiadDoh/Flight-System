INSERT INTO 
    "User_Roles" ("role_name")
VALUES
    ('customers'),
    ('administrators'),
    ('airline_comapny');




INSERT INTO 
    "Countries"("name")
VALUES
    ('Afghanistan'),
    ('Bolivia'),
	('Chile'),
	('Eritrea'),
	('Grenada'),
	('Honduras'),
	('Japan'),
	('Luxembourg'),
	('Maldives'),
	('Mali'),
	('Nigeria'),
    ('Peru');


INSERT INTO 
    "Users" ("username","password","email","user_role")
VALUES
    ('Liad','123456','Liad@gmail.com',1),
    ('Roee','123456','Roee@gmail.com',2),
	('Eliran','123456','Eliran@gmail.com',3),
	('Bar','123456','Bar@gmail.com',1),
	('Almog','123456','Almog@gmail.com',2),
	('Niv','123456','Niv@gmail.com',3),
	('Ronen','123456','Ronen@gmail.com',1),
	('Liran','123456','Liran@gmail.com',2),
	('Asif','123456','Asif@gmail.com',3);
    

INSERT INTO 
    "Customers" ("first_name","last_name","address","phone_no","credit_card_no","user_id")
VALUES
    ('Liad','Yese','Yese',0501111111,1,1),
    ('Bar','Yese','Yese',0501111112,2,4),
    ('Ronen','Yese','Yese',0501111113,3,7);


INSERT INTO 
    "Administrators" ("first_name","last_name","user_id")
VALUES
    ('Roee','Yese',2),
    ('Almog','Yese',5),
    ('Liran','Yese',8);

INSERT INTO 
    "Airline_Companies" ("name","country_id","user_id")
VALUES
    ('Eliran',1,3),
    ('Niv',2,6),
    ('Asif',3,9);



INSERT INTO 
    "Flights" ("airline_company_id","origin_country_id","destination_country_id","departure_time","landing_time","remaining_tickets")
VALUES
    (1,1,2,now(),now()+ '1 Hour'::INTERVAL,100),
    (2,2,3,now(),now()+'2 Hour'::INTERVAL,100),
    (3,3,4,now(),now()+'3 Hour'::INTERVAL,100),
    (1,4,5,now(),now()+'4 Hour'::INTERVAL,100),
    (2,5,6,now(),now()+'5 Hour'::INTERVAL,100),
    (3,6,7,now(),now()+'6 Hour'::INTERVAL,100);

INSERT INTO 
    "Tickets" ("flight_id","customer_id")
VALUES

	(1,1),
	(2,2),
	(3,3),
	(4,1);
