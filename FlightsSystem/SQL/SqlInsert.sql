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
    "Users" ("username","password","email","user_Role")
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
    "Customers" ("first_name","last_name","address",phone_no,"credit_card_no","user_id")


INSERT INTO 
    "Administrators" ("first_name","last_name","user_id")

INSERT INTO 
    "Airline_Companies" ("name","country_id","user_id")


INSERT INTO 
    "Flights" ("airline_company_id","origin_country_id","destination_country_id","departure_time","landing_time","remaining_tickets")


INSERT INTO 
    "Tickets" ("flight_Id","coustomer_Id")
VALUES

	(1,1),
	(2,2),
	(3,3),
	(4,1);
