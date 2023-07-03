package com.test.simpleCRUD;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUserData extends UserServiceHelper{
	
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
	
	@Test(dependsOnMethods = "validLogin")
	public static void deleteUserData() {
		Response response = deleteData();
		ReusableMethods.verifyStatusCodeis(response, 200);
	}

}
