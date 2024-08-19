package com.spotify.playlist.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.playlist.api.Routes.BASE_PATH;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec()
    {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI"))
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();

    }

    public static ResponseSpecification getResponseSpec(int expectedStatusCode)
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .log(LogDetail.ALL).build();

    }

    public static RequestSpecification getRequestSpecForOAuthApi()
    {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("OAUTH_BASE_URI"))
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();

    }
}
