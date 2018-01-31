package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbums(AlbumInfo albumInfo) {
        restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
    }

    public void deleteAlbums(AlbumInfo albumInfo) {
        restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
    }

    public List<AlbumInfo> findAllAlbums() {
        return restOperations.exchange(albumsUrl, HttpMethod.GET, null, albumListType).getBody();
    }

    public List<AlbumInfo> getAlbum(long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumsUrl)
                .queryParam("id", id);

        return restOperations.exchange(builder.toUriString(), HttpMethod.GET, null, albumListType).getBody();
    }

}
