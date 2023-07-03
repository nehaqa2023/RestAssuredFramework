package com.test.helpers;

import java.util.concurrent.TimeUnit;

import com.test.models.UserDataPOJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static UserDataPOJO user;
	
	public static UserDataPOJO getUserData() {
		user = new UserDataPOJO();
		user.setAccountno("TA-ZXCVBNB");
		user.setDepartmentno("9");
		user.setSalary("100000");
		user.setPincode("999999");
		return user;
	}
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
	public String getContentType(Response response) {
		return response.getContentType();
	}
	
	public long getResponseTimeIn(Response response, TimeUnit time ) {
		return response.getTimeIn(time);
	}
	
	public static void verifyStatusCodeis(Response response, int statusCode) {
		response.then().statusCode(statusCode);
	}

	public static String getJSONPathData(Response response, String string) {
		return response.body().jsonPath().getString(string);
	}

}
