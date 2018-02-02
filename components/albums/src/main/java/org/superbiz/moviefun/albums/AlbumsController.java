package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    Logger logger= LoggerFactory.getLogger("AlbumsController");

    private AlbumsRepository albumsRepository;

    public AlbumsController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        logger.info("inside addAlbum" + album.getArtist());
        albumsRepository.addAlbum(album);
    }

    @GetMapping
    public List<Album> index() {
        return albumsRepository.getAlbums();
    }

    @GetMapping("/{albumId}")
    public Album details(@PathVariable long albumId) {
        return albumsRepository.find(albumId);
    }
}
