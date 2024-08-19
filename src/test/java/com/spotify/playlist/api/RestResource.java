package com.spotify.playlist.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.playlist.api.Routes.*;
import static com.spotify.playlist.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String access_token, Object requestPayload,
                                String path, int expectedStatusCode)
    {
        return given(getRequestSpec())
                .auth().oauth2(access_token)
                .body(requestPayload)
                .when().post(path)
                .then().spec(getResponseSpec(expectedStatusCode))
                .extract().response();
    }


    public static Response get(String access_token,
                               String path, int expectedStatusCode)
    {
        return given(getRequestSpec())
                .auth().oauth2(access_token)
                .when().get(path)
                .then().spec(getResponseSpec(expectedStatusCode))
                .extract().response();
    }

    public static void put(String access_token, String path,
                          Object requestPayload, int expectedStatusCode)
    {
        given(getRequestSpec())
                .auth().oauth2(access_token)
                .body(requestPayload)
                .when().put(path)
                .then().spec(getResponseSpec(expectedStatusCode));
    }

    public static Response oauth(HashMap<String,String> formParams)
    {
        return given(getRequestSpecForOAuthApi())
                .formParams(formParams)
                .when().post(API+TOKEN)
                .then().extract().response();
    }
}
