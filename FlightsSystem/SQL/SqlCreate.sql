CREATE TABLE "Countries"(
	"id" serial,
	"name" text UNIQUE NOT NULL,
	"flag" bytea UNIQUE,                                     
	PRIMARY KEY("id")
);

CREATE TABLE "User_Roles"(
	"id" serial,
	"role_name" text UNIQUE NOT NULL,
	PRIMARY KEY("id")
);

CREATE TABLE "Users"(
	"id" bigserial ,
	"username" text UNIQUE NOT NULL,
	"password" text NOT NULL,
	"email" text UNIQUE NOT NULL,
	"user_role" int NOT NULL,
	"thumbnail" bytea UNIQUE,
	PRIMARY KEY("id"),
	FOREIGN KEY("user_role") 
	  REFERENCES "User_Roles"("id")
	
);

CREATE TABLE "Customers"(
	"id" bigserial,
	"first_name" text NOT NULL,
	"last_name" text NOT NULL,
	"address" text NOT NULL,
	"phone_no" text UNIQUE NOT NULL,
	"credit_card_no" text UNIQUE NOT NULL,
	"user_id" bigint UNIQUE NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("user_id") 
	  REFERENCES "Users"("id")
	
);

CREATE TABLE "Administrators"(
	"id" serial,
	"first_name" text NOT NULL,
	"last_name" text NOT NULL,
	"user_id" bigint UNIQUE NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("user_id") 
	  REFERENCES "Users"("id")
);

CREATE TABLE "Airline_Companies"(
	"id" bigserial,
	"name" text UNIQUE NOT NULL,
	"country_id" int NOT NULL,
	"user_id" bigint UNIQUE NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("user_id") 
	  REFERENCES "Users"("id"),
	FOREIGN KEY("country_id") 
	  REFERENCES "Countries"("id")
);



CREATE TABLE "Flights" (
    "id" bigserial,
    "airline_company_id" bigint NOT NULL,
    "origin_country_id" int NOT NULL,
	"destination_country_id" int NOT NULL,
	"departure_time" timestamp NOT NULL,
	"landing_time" timestamp NOT NULL,
	"remaining_tickets" int NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("airline_company_id") 
	  REFERENCES "Airline_Companies"("id"),
	FOREIGN KEY("origin_country_id") 
	  REFERENCES "Countries"("id"),
	FOREIGN KEY("destination_country_id") 
	  REFERENCES "Countries"("id")
);

CREATE TABLE "Tickets"(
	"id" bigserial,
	"flight_id" bigint NOT NULL,
	"customer_id" bigint NOT NULL,
	PRIMARY KEY("id"),
	FOREIGN KEY("flight_id") 
	  REFERENCES "Flights"("id"),
	FOREIGN KEY("customer_id") 
	  REFERENCES "Customers"("id"),
	UNIQUE("flight_id","customer_id")
);

ALTER SEQUENCE "User_Roles_id_seq" RESTART WITH 1;
ALTER SEQUENCE "Countries_id_seq" RESTART WITH 1;
ALTER SEQUENCE "Administrators_id_seq" RESTART WITH 1;
ALTER SEQUENCE "Tickets_id_seq" RESTART WITH 1;
ALTER SEQUENCE "Users_id_seq" RESTART WITH 1;
ALTER SEQUENCE "Flights_id_seq" RESTART WITH 1;
ALTER SEQUENCE "Airline_Companies_id_seq" RESTART WITH 1;

