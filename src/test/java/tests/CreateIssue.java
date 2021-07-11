package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssue extends Base{
	
	String jwtoken, customerId, quoteId;
	
	Base base = new Base();	
	
	@BeforeTest
	public void authenticate(){
		JsonPath jp = base.getJWToken();
		jwtoken = jp.get("response.customer.jwtToken").toString();
		customerId = jp.get("response.customer.customerId").toString();
	}
	
	@Test(enabled=true)
	public void addItem(){
		RestAssured.baseURI=base.baseURI;
		Response res=given().header("Content-Type","application/json").
		header("token","KEY "+jwtoken).
		header("x-header-token", base.userEmail).
		body("{\"storeId\":1,\"source\":0,\"customerId\":"+customerId+","
				+ "\"addToQuoteProductsRequests\":[{\"sku\":\""+base.sku+"\",\"parentSku\":\""+base.parentSku+"\","
				+ "\"quantity\":1,\"overrideQuantity\":true}]}").when().
		post(base.quoteURL).then().assertThat().statusCode(200).extract().response();
		JsonPath jp=getJson(res);
		quoteId = jp.get("quoteId").toString();
		Assert.assertEquals(jp.get("statusMsg"), "Success!");
	}
	
	@Test(dependsOnMethods = {"addItem"})
	public void updateQuantity(){
		RestAssured.baseURI=base.baseURI;
		Response res=given().header("Content-Type","application/json").
		header("token","KEY "+jwtoken).
		header("x-header-token", base.userEmail).
		body("{\"sku\":\""+base.sku+"\",\"quantity\":2,\"quoteId\":\"+quoteId+\",\"storeId\":1,\"customerId\":"+customerId+"}").when().
		put(base.quoteURL).then().assertThat().statusCode(200).extract().response();
		JsonPath jp=getJson(res);
		Assert.assertEquals(jp.get("statusMsg"), "Success");
	}
}
