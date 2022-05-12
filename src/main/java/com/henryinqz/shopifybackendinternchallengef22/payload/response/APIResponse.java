package com.henryinqz.shopifybackendinternchallengef22.payload.response;

public class APIResponse {
    public boolean success;
    public String message;

    public APIResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
