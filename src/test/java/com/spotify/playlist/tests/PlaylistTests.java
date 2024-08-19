package com.spotify.playlist.tests;

import com.spotify.playlist.api.StatusCode;
import com.spotify.playlist.api.applicationApi.PlaylistApi;
import com.spotify.playlist.pojo.Error;
import com.spotify.playlist.pojo.Playlist;
import com.spotify.playlist.utils.FakerUtils;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static com.spotify.playlist.utils.DataLoader.getDataLoaderInstance;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify playlist tests")
@Feature("Playlists tests")
public class PlaylistTests extends BaseTest{

    @Story("Create a playlist")
    @TmsLink("TC#21")
    @Issue("Issue#125")
    @Severity(CRITICAL)
    @Link(name = "TestLink", url = "https://dev.example.com/")
    @Description("Test to validate creation of a playlist")
    @Test(description = "create playlist api")
    public void shouldBeAbleToCreateAPlaylist()
    {
        String playlistName = FakerUtils.generateName();
        String playlistDesc = FakerUtils.generateDescription();

        Playlist payload = Playlist.builder()
                .name(playlistName)
                .description(playlistDesc)
                ._public(false).build();

        Playlist response =  PlaylistApi.post(payload, StatusCode.CODE_201.getCode())
                            .as(Playlist.class);

        assertThat(response.getName(),equalTo(playlistName));
        assertThat(response.getDescription(),equalTo(playlistDesc));
        assertThat(response.get_public(),equalTo(false));
    }

    @Test
    public void shouldBeAbleToGetAPlaylist()
    {

        Playlist response =  PlaylistApi.get(getDataLoaderInstance()
                            .getDataPropertyValue("get_Playlist_Id"),
                            StatusCode.CODE_200.getCode())
                            .as(Playlist.class);

         assertThat(response.getName(),equalTo("Playliste^GOqPkebM"));
         assertThat(response.getDescription(),equalTo("DescriptionpaoHlJtr47@SZL,WLsng6Qo,K"));
         assertThat(response.get_public(),equalTo(true));

    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist()
    {
        Playlist payload = Playlist.builder()
                .name("Updated Playlist Name")
                .description("Updated playlist description")
                ._public(true).build();

        PlaylistApi.put(getDataLoaderInstance()
                .getDataPropertyValue("update_Playlist_Id"),
                payload,StatusCode.CODE_200.getCode());
    }

    @Story("Create a playlist")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName()
    {
        String playlistDesc = FakerUtils.generateDescription();

        Playlist payload = Playlist.builder()
                .name("")
                .description(playlistDesc)
                ._public(false).build();

        Error response = PlaylistApi.post(payload,StatusCode.CODE_400.getCode()).as(Error.class);

        assertThat(response.getError().getStatus(),equalTo(StatusCode.CODE_400.getCode()));
        assertThat(response.getError().getMessage(),equalTo(StatusCode.CODE_400.getMsg()));
    }

    @Story("Create a playlist")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken()
    {
        String expired_token = "BQCedyg9FbNJtwhWlG0qmMGgySAxUcnT7tUbU0aWJYPyQoK1BrXtELQIMX_o0zK36AFESTp0Z25I4v68w65Q2PeYRzTF4mdAaVb2itnTZT8vYjdw7dD3BcAaGb69gW4QCChTTBQs6f7AYBamIJrSuHZbTHm7IWEDluOfEoZQXeeuZpYo1elWohIBhlhd19xxwELNCwPELPydxopZSLZchq-dmoRHnXr26YK730Q3ET8vV0XQBxMVgoTogQBeqSUGHAYy8UIyIkgVqDB9OQ";

        Playlist payload = Playlist.builder()
                .name("Playlist One")
                .description("My First playlist")
                ._public(false).build();

        Error response = PlaylistApi.post(expired_token,
                        payload,StatusCode.CODE_401.getCode()).as(Error.class);

        assertThat(response.getError().getStatus(),equalTo(StatusCode.CODE_401.getCode()));
        assertThat(response.getError().getMessage(),equalTo(StatusCode.CODE_401.getMsg()));
    }

}
