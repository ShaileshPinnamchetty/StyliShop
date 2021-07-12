package com.stylishop.tests.pojo;

public class ChangeQuantityRequest {
    private String sku;
    private int quantity;
    private String quoteId;
    private int storeId;
    private Integer customerId;

    public ChangeQuantityRequest(String sku, int quantity, String quoteId, int storeId, Integer customerId) {
        this.sku = sku;
        this.quantity = quantity;
        this.quoteId = quoteId;
        this.storeId = storeId;
        this.customerId = customerId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
