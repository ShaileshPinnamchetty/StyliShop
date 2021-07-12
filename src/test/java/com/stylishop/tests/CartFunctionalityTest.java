package com.stylishop.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylishop.tests.pojo.AddItemRequest;
import com.stylishop.tests.pojo.ChangeQuantityRequest;
import com.stylishop.tests.pojo.Product;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CartFunctionalityTest {

    String jwtToken, quoteId;
    Integer customerId;
    Base base = new Base();

    @BeforeTest
    public void authenticate() {
        JsonPath jp = base.getJWToken();
        jwtToken = jp.get("response.customer.jwtToken").toString();
        customerId = jp.get("response.customer.customerId");
    }

    @Test(enabled = true)
    public void addItem() throws JsonProcessingException {
        RestAssured.baseURI = base.getBaseURI();
        Response response = given().header("Content-Type", "application/json").
                header("token", "KEY " + jwtToken).
                header("x-header-token", base.getUserEmail()).
                body(getAddItemJson()).when().
                post(base.getQuoteURL()).then().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = base.getJson(response);
        quoteId = jsonPath.get("quoteId").toString();
        Assert.assertEquals(jsonPath.get("statusMsg"), "Success!");
    }


    public String getAddItemJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(getAddItemRequest());
    }

    public AddItemRequest getAddItemRequest() {
        return new AddItemRequest(1, 0, customerId, getProduct());
    }

    private List<Product> getProduct() {
        List<Product> products = new ArrayList<Product>();
        Product product = new Product();
        product.setSku("886952780272");
        product.setParentSku("50335601");
        product.setQuantity(1);
        product.setOverrideQuantity(true);
        products.add(product);
        return products;
    }

    @Test(dependsOnMethods = {"addItem"})
    public void updateQuantity() throws JsonProcessingException {
        RestAssured.baseURI = base.getBaseURI();
        Response response = given().header("Content-Type", "application/json").
                header("token", "KEY " + jwtToken).
                header("x-header-token", base.getUserEmail()).
                body(getUpdateQuantityJson()).when().
                put(base.getQuoteURL()).then().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = base.getJson(response);
        Assert.assertEquals(jsonPath.get("statusMsg"), "Success");
    }

    public String getUpdateQuantityJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(getChangeQuantityRequest());
    }

    public ChangeQuantityRequest getChangeQuantityRequest() {
        return new ChangeQuantityRequest(
                "886952780272",
                2,
                quoteId,
                1,
                customerId);
    }


}
