package com.stylishop.tests.pojo;

import java.util.List;

public class AddItemRequest {
    int storeId;
    int source;
    int customerId;
    List<Product> addToQuoteProductsRequests;

    public AddItemRequest(int storeId, int source, int customerId, List<Product> addToQuoteProductsRequests) {
        this.storeId = storeId;
        this.source = source;
        this.customerId = customerId;
        this.addToQuoteProductsRequests = addToQuoteProductsRequests;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Product> getAddToQuoteProductsRequests() {
        return addToQuoteProductsRequests;
    }

    public void setAddToQuoteProductsRequests(List<Product> addToQuoteProductsRequests) {
        this.addToQuoteProductsRequests = addToQuoteProductsRequests;
    }
}
