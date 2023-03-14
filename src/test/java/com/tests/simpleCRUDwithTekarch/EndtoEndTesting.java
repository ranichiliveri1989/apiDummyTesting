package com.tests.simpleCRUDwithTekarch;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.models.AddEmployeePOJO;
import com.test.models.CreateResponsePOJO;
//import com.test.models.UserPOJO;
import com.tests.utils.PropertiesUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EndtoEndTesting {
//String token;
	
	static int newId;
	@BeforeClass
	public void init() throws IOException {
		
		String bURI=PropertiesUtility.readPropertyData("uri");
		RestAssured.baseURI=bURI;
	}
	
	@Test(priority = 0)
	public static void getData() throws IOException {
	
		Response res= RestAssured
	             .when()
				.get(Endpoints.GET_DATA);

		res.prettyPrint();
		
		res.then().statusCode(200);
	     String status=res.body().jsonPath().get("status");
		 System.out.println("status:"+status);
		   int  datarecords=res.body().jsonPath().get("data.size()");
	    	System.out.println("all records =="+datarecords);
	    		

    	
	}
	
	 @Test(priority = 1)
	
      public static void addUserData() throws IOException {
		
    	  String name2=PropertiesUtility.readPropertyData("name1");
  		String salary=PropertiesUtility.readPropertyData("salary1");
  		String age=PropertiesUtility.readPropertyData("age1");
  		AddEmployeePOJO ob=new AddEmployeePOJO();
  		ob.setName(name2);
  		ob.setSalary(salary);
  		ob.setAge(age);
  		
		Response res= RestAssured
		               .given()
		               .log()
		               .headers()
		               .contentType(ContentType.JSON)
			           .body(ob)
		               .when()
		               .post(Endpoints.ADD_DATA);
		//res.then().statusCode(200);
		JsonPath jsonPathEvaluator = res.body().jsonPath();
		res.prettyPrint();

		newId = jsonPathEvaluator.get("data.id");
		System.out.println("newId" + newId);
		Assert.assertEquals(jsonPathEvaluator.get("data.age"), age);
		Assert.assertEquals(jsonPathEvaluator.get("data.salary"), salary);
		Assert.assertEquals(jsonPathEvaluator.get("data.name"), name2);
       res.then().statusCode(200);

	}
	 @Test(dependsOnMethods = "addUserData",priority = 2)
	 
    public static void deleteUserData() 
    {
    	
    	System.out.println("deleting " + newId);
		Response res= RestAssured
		              .given()
		              .log()
		               .headers().pathParam("id", newId)
		               .contentType(ContentType.JSON)
		               .when()
		               .delete(Endpoints.DELETE_DATA);
		
		res.then().statusCode(200);
		
		res.prettyPrint();
    }
    @Test(priority = 3)
    
	 public static void deleteUserData2() 
	    {
	    	
	    	int idvalue=0;
			Response res= RestAssured
			              .given()
			              .log()
			               .headers().pathParam("id",idvalue)
			               .contentType(ContentType.JSON)
			               .when()
			               .delete(Endpoints.DELETE_DATA);
			
			res.then().statusCode(400);
			
			res.prettyPrint();
			//return res;
	    }
    @Test(priority = 4)
    
    public static void getDataForid2() throws IOException {
    	
		Response res= RestAssured
	             
	             .given()
	              .log()
	               .headers().pathParams("id",2)
	               .contentType(ContentType.JSON)
	               .when()
				.get(Endpoints.GET_DATA);

		res.prettyPrint();
		
		res.then().statusCode(200);
		
		ArrayList<String> allIds=res.body().jsonPath().get("findAll{it->it.id=='2'}");
		System.out.println("all ids:"+allIds);

    	
	}
    

	
}



