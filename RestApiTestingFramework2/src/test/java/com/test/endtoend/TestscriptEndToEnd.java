package com.test.endtoend;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;
import com.test.models.UserDataPOJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestscriptEndToEnd extends UserServiceHelper{
	
	@BeforeMethod
	public static void baseUri() {
		RestAssured.baseURI = getBaseUri();
	}
	
	@Test(priority=1)
	public static void TC_001_validLogin() {
		String token = getToken();
		UserServiceHelper.token = token;
		log.info("Token recevied successfully.");
		Assert.assertTrue(token != null);
	}
	
	@Test(priority=2, enabled=true)
	public static void TC_002_createUserData() {
		Response response = createUserData();
		ReusableMethods.verifyStatusCodeis(response, 201);
		response.prettyPrint();
        String status = ReusableMethods.getJSONPathData(response, "status");
        System.out.println("status: "+status);
        Assert.assertEquals(status, "success");
	}
	
	@Test(priority=3, enabled=true)
	public static void TC_003_getUserData() {
		List<UserDataPOJO> list = getAllUsers();
		Assert.assertEquals(list.size()>0, true);
		
		System.out.println("Account No: "+list.get(0).getAccountno());
		System.out.println("Id: "+list.get(0).getId());
	}
	
	@Test(priority=4, enabled=true)
	public static void TC_004_updateUserData() {
		Response response = updateUserData();
		ReusableMethods.verifyStatusCodeis(response, 200);
	}
	
	@Test(priority=5, enabled=true)
	public static void TC_005_deleteUserData() {
		Response response = deleteData();
		ReusableMethods.verifyStatusCodeis(response, 200);
	}

}
