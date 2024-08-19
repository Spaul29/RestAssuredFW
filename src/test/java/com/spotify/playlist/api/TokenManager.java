package com.spotify.playlist.api;

import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

import static com.spotify.playlist.utils.ConfigLoader.getConfigInstance;

public class TokenManager {

    private static String access_token;
    private static Instant expiryTime;

    public synchronized static String getAccessToken()
    {
        try {
            if (Objects.isNull(access_token) || Instant.now().isAfter(expiryTime)) {
                System.out.println("Renewing token  ...");
                Response response = getRenewTokenResponse();
                access_token = response.path("access_token");
                int expiryDurationInSec = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSec - 300);
            } else {
                System.out.println("Token is good to use !!");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error occurred in Token generation");
        }
        return access_token;
    }

    private static Response getRenewTokenResponse() {

        HashMap<String,String> formParams = new HashMap<String, String>();
        formParams.put("client_id",getConfigInstance().getConfigPropertyValue("client_id"));
        formParams.put("client_secret",getConfigInstance().getConfigPropertyValue("client_secret"));
        formParams.put("grant_type",getConfigInstance().getConfigPropertyValue("grant_type"));
        formParams.put("refresh_token",getConfigInstance().getConfigPropertyValue("refresh_token"));

        Response response = RestResource.oauth(formParams);

        if(response.statusCode() != 200)
        {
            throw new RuntimeException("Error occurred in Token generation");
        }

        return response;
    }
}
