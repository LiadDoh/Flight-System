package com.FlightsSystem;

import Models.Customers;
import Models.Flights;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
class FlightsSystemApplicationTests {

	private HttpClient client = HttpClient.newHttpClient();

	@Test
	void contextLoads() {
	}

	@Test
	void airlineControllerTest(){
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/coupon/1"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

//		var expected =
//				gson.fromJson(response.body(), Flights.class);

		//var current = new Flights();

		//Assert.assertEquals(current,expected);

	}

	@Test
	void customersControllerTest(){
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/coupon/1"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

//		var expected =
//				gson.fromJson(response.body(), Customers.class);

//		var current = new Customers();
//
//		Assert.assertEquals(current,expected);

	}

	@Test
	void baseControllerTest(){
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/coupon/1"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

//		var expected =
//				gson.fromJson(response.body(),.class);
//
//		var current = new CouponDTO(1,"coffee","foods",13);
//
//		Assert.assertEquals(current,expected);

	}

	@Test
	void administratorControllerTest(){
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/coupon/1"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

//		var expected =
//				gson.fromJson(response.body(),CouponDTO.class);
//
//		var current = new CouponDTO(1,"coffee","foods",13);
//
//		Assert.assertEquals(current,expected);

	}

}
