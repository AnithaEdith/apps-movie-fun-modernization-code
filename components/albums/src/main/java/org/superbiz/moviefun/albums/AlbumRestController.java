package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/albumservice")
public class AlbumRestController {

    private AlbumsRepository albumsRepository;
    Logger logger= LoggerFactory.getLogger(this.getClass());

    AlbumRestController(AlbumsRepository albumsRepository){
        this.albumsRepository=albumsRepository;
    }

    @PostMapping
    public void addAlbums(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @DeleteMapping
    public void deleteAlbums(@RequestBody Album album ) {
        albumsRepository.deleteAlbum(album);
    }


    @GetMapping()
    public List<Album> get(@RequestParam(name = "id", required = false) Long id) {
        logger.info("Inside get albums");
        if(id!=null) {
            List<Album> albums=new ArrayList<Album>();
            albums.add(albumsRepository.find(id));
            return albums;

        }
        else{
            logger.info("Inside get albums else");
            return albumsRepository.getAlbums();
        }
    }


}
