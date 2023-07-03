package com.test.helpers;

import com.test.utils.Utils;
import com.test.utils.Log4JUtility;
import com.test.constants.EndPoints;
import com.test.models.LoginDataPOJO;
import com.test.models.UserDataPOJO;
import com.test.helpers.ReusableMethods;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class UserServiceHelper {

	private static Response response;
	protected static String token = null;
	protected static Log4JUtility logObject = Log4JUtility.getInstance();
	protected static Logger log;

	public static String getBaseUri() {
		String baseURI = Utils.getConfigProperty("url");
		log = logObject.getLogger();
		return baseURI;
	}

	public static Response LoginToApplication() {
		String username = Utils.getConfigProperty("username");
		String password = Utils.getConfigProperty("password");
		
		LoginDataPOJO ob = new LoginDataPOJO(username, password);
		response = RestAssured.
				given().
				contentType("application/json").
				body(ob).
				when().
				post(EndPoints.LOGIN);
		return response; 
	}
		
	public static String getToken() {
		response = LoginToApplication();
		token = response.jsonPath().get("[0].token");
		return token;
		
	}
		
	public static List<UserDataPOJO> getAllUsers() {
	    System.out.println("getAllUsers: token: "+token);
		Header header = new Header("token", token);
		
		response = RestAssured.given()
				.header(header)
				.when()
				.get(EndPoints.GET_DATA);
		
		//ReusableMethods.verifyStatusCodeis(response, 200);
		
		response.then().contentType(ContentType.JSON);
		
		UserDataPOJO[] userArray = response.as(UserDataPOJO[].class);
		List<UserDataPOJO> list = Arrays.asList(userArray);
		

		System.out.println("total number of records="+response.body().jsonPath().get("size()"));
		return list;
	
	}
	
	public static Response createUserData() {
		Header header = new Header("token" , token);
		UserDataPOJO user = ReusableMethods.getUserData();
		response = RestAssured.given().contentType(ContentType.JSON)
				//.body("{\"accountno\":\"TA-ZXCVBNA\",\"departmentno\":\"1\",\"salary\":\"100000\",\"pincode\":\"999999\"}")
				.body(user)
				.header(header)
				.when()
				.post(EndPoints.ADD_DATA);
		
		return response;
	}
	
	public static Response updateUserData() {
		Header header = new Header("token" , token);
		List<UserDataPOJO> userDataList = getAllUsers();
		UserDataPOJO userData = ReusableMethods.getUserData();

		for (UserDataPOJO data : userDataList) {
			if(data.getAccountno().equals(ReusableMethods.user.getAccountno())) {
				userData.setUserid(data.getUserid());
				userData.setId(data.getId());
			}
		}
		System.out.println("Current salary: "+userData.getSalary());
		userData.setSalary("125000");
		
		response = RestAssured.given().contentType(ContentType.JSON)
		.body(userData)
		.header(header)
		.when()
		.put("updateData");
		
		return response;
		
	}
	
	public static Response deleteData() {
		Header header = new Header("token", token);
		
		List<UserDataPOJO> userDataList = getAllUsers();
		UserDataPOJO userData = ReusableMethods.getUserData();

		for (UserDataPOJO data : userDataList) {
			if(data.getAccountno().equals(ReusableMethods.user.getAccountno())) {
				userData.setUserid(data.getUserid());
				userData.setId(data.getId());
			}
		}
		
		response = RestAssured.given().contentType(ContentType.JSON)
				.body(userData)
				.header(header)
				.when()
				.delete("deleteData");
		
		return response;
	}
	
	

}
