package com.stylishop.tests.pojo;

public class Product {
    String sku;
    String parentSku;
    int quantity;
    boolean overrideQuantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOverrideQuantity() {
        return overrideQuantity;
    }

    public void setOverrideQuantity(boolean overrideQuantity) {
        this.overrideQuantity = overrideQuantity;
    }
}
