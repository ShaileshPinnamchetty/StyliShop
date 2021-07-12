package com.stylishop.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Base {

    private Properties properties;
    private String baseURI, userEmail, password, sku, parentSku, quoteURL;

    public JsonPath getJson(Response response) {
        String stringResponse = response.asString();
        return new JsonPath(stringResponse);
    }

    public void getProperties() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
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

    public JsonPath getJWToken() {
        getProperties();
        RestAssured.baseURI = getBaseURI();
        Response res = given().header("Content-Type", "application/json").
                body("{\"isStandaloneLogin\":false,\"loginType\":\"EMAIL_LOGIN\",\"password\":\"" + password + "\","
                        + "\"useridentifier\":\"" + userEmail + "\",\"gender\":2,\"ageGroupId\":1}").when().
                post("/rest/customer/v4/login").then().assertThat().statusCode(200).extract().response();
        return getJson(res);
    }


    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getParentSku() {
        return parentSku;
    }

    public void setParentSku(String parentSku) {
        this.parentSku = parentSku;
    }

    public String getQuoteURL() {
        return quoteURL;
    }

    public void setQuoteURL(String quoteURL) {
        this.quoteURL = quoteURL;
    }
}
