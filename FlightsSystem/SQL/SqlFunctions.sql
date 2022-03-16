create or replace function get_airline_by_username(_username text)
returns table("id" bigint,"name" text,"country_id" integer,"user_id" bigint)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select "Airline_Companies"."id","Airline_Companies"."name","Airline_Companies"."country_id","Airline_Companies"."user_id"
 			from "Airline_Companies"
 			join "Users"
 			on "Airline_Companies"."user_id" = "Users"."id"
 			where "Users"."username" = _username;
 	end;
$$



create or replace function get_customer_by_username(_username text)
returns table("id" bigint,"first_name" text,"last_name" text,"address" text,"phone_no" text,"credit_card_no" text,"user_id" bigint)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select "Customers"."id","Customers"."first_name","Customers"."last_name","Customers"."address","Customers"."phone_no","Customers"."credit_card_no","Customers"."user_id"
 			from "Customers"
 			join "Users"
 			on "Customers"."user_id" = "Users"."id"
 			where "Users"."username" = _username;
 	end;
$$   


create or replace function get_user_by_username(_username text)
returns table("id" bigint,"username" text,"passsword" text,"email" text,"user_role" integer,"thumbnail" bytea)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select *
			from "Users"
			where "Users"."username" = _username;
 	end;
$$



create or replace function get_flights_by_parameters(_origin_country_id int, _destination_country_id int,_date date)
returns table("id" bigint,"airline_company_id" bigint,"origin_country_id" integer,"destination_country_id" integer,"departure_time" timestamp,"landing_time" timestamp,"remaining_tickets" integer)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select *
			from "Flights"
			where "Flights"."origin_country_id" = _origin_country_id and
			"Flights"."destination_country_id" = _destination_country_id and
			DATE("Flights"."departure_time") = _date;
 	end;
$$



create or replace function get_flights_by_airline_id(_airline_id bigint)
returns table("id" bigint,"airline_company_id" bigint,"origin_country_id" integer,"destination_country_id" integer,"departure_time" timestamp,"landing_time" timestamp,"remaining_tickets" integer)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select *
			from "Flights"
			where "Flights"."airline_company_id" = _airline_id;
 	end;
$$




create or replace function get_arrival_flights(_country_id int)
returns table("id" bigint,"airline_company_id" bigint,"origin_country_id" integer,"destination_country_id" integer,"departure_time" timestamp,"landing_time" timestamp,"remaining_tickets" integer)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select *
			from "Flights"
			where "Flights"."destination_country_id" = _country_id and "Flights"."landing_time" between Now() and Now() + '12 Hours'::interval;
 	end;
$$



create or replace function get_departure_flights(_country_id int)
returns table("id" bigint,"airline_company_id" bigint,"origin_country_id" integer,"destination_country_id" integer,"departure_time" timestamp,"landing_time" timestamp,"remaining_tickets" integer)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select *
			from "Flights"
			where "Flights"."origin_country_id" = _country_id and "Flights"."departure_time" between Now() and Now() - '12 Hours'::interval;
 	end;
$$

create or replace function get_tickets_by_customer(_customer_id bigint)
returns table("id" bigint,"flight_id" bigint,"customer_id" bigint)
LANGUAGE plpgsql
AS
$$
	begin
 		return query
 			select *
			from "Tickets"
			where "Tickets"."customer_id" = _customer_id;
 	end;
$$














