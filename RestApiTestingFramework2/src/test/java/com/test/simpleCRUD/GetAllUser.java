package com.test.simpleCRUD;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.helpers.UserServiceHelper;
import com.test.models.UserDataPOJO;

import io.restassured.RestAssured;

public class GetAllUser extends UserServiceHelper{
	
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
	public static void getUserData() {
		List<UserDataPOJO> list = getAllUsers();
		AssertJUnit.assertEquals(list.size()>0, true);
		
		System.out.println("Account No: "+list.get(0).getAccountno());
		System.out.println("Id: "+list.get(0).getId());
	}
	

}
