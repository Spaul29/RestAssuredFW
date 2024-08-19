package com.spotify.playlist.api.applicationApi;

import com.spotify.playlist.api.RestResource;
import com.spotify.playlist.pojo.Playlist;
import io.restassured.response.Response;

import static com.spotify.playlist.api.Routes.*;
import static com.spotify.playlist.api.TokenManager.getAccessToken;
import static com.spotify.playlist.utils.ConfigLoader.getConfigInstance;

public class PlaylistApi {

    public static Response post(Playlist playlistRequest, int expectedStatusCode)
    {
        return RestResource.post(getAccessToken(),playlistRequest,
                USERS+"/"+getConfigInstance().getConfigPropertyValue("user_id")+PLAYLISTS,expectedStatusCode);
    }

    public static Response post(String access_token,Playlist playlistRequest, int expectedStatusCode)
    {
        return RestResource.post(access_token,playlistRequest,
                USERS+"/"+getConfigInstance().getConfigPropertyValue("user_id")+PLAYLISTS,expectedStatusCode);
    }

    public static Response get(String playlistId, int expectedStatusCode)
    {
        return RestResource.get(getAccessToken(),PLAYLISTS+"/"+playlistId,expectedStatusCode);
    }

    public static void put(String playlist_id, Playlist playlistRequest, int expectedStatusCode)
    {
         RestResource.put(getAccessToken(),
                 PLAYLISTS+"/"+playlist_id, playlistRequest,expectedStatusCode);

    }
}
