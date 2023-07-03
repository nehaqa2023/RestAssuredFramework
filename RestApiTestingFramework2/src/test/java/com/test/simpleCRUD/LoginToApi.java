package com.test.simpleCRUD;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;

public class LoginToApi extends UserServiceHelper{
	
	@BeforeMethod
	public static void baseUri() {
		RestAssured.baseURI = getBaseUri();
		System.out.println("BaseURI "+token);
	}
	
	@Test
	public static void validLogin() {
		String token = getToken();
		UserServiceHelper.token = token;
		System.out.println("Token "+token);
		AssertJUnit.assertTrue(token != null);
	}
	

}
