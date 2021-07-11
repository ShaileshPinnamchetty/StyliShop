package tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Base {
	
	Properties properties;
	String baseURI, userEmail, password, sku, parentSku, quoteURL;
	
	public JsonPath getJson(Response r){
		String str_res=r.asString();
		JsonPath jp=new JsonPath(str_res);
		return jp;
	}	
	
	public void getProperties(){
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prop.properties");
		properties = new Properties();
		try {
			properties.load(inputStream);
			baseURI = properties.getProperty("baseURI");
			userEmail = properties.getProperty("userEmail");
			password = properties.getProperty("password");
			sku = properties.getProperty("sku");
			parentSku = properties.getProperty("parentSku");
			quoteURL = properties.getProperty("quoteURL");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JsonPath getJWToken(){
		getProperties();
		RestAssured.baseURI=baseURI;
		Response res=given().header("Content-Type","application/json").
		body("{\"isStandaloneLogin\":false,\"loginType\":\"EMAIL_LOGIN\",\"password\":\""+password+"\","
				+ "\"useridentifier\":\""+userEmail+"\",\"gender\":2,\"ageGroupId\":1}").when().
		post("/rest/customer/v4/login").then().assertThat().statusCode(200).extract().response();
		return getJson(res);
	}
}
