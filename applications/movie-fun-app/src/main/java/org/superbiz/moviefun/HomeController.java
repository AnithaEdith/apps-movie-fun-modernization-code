package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.albumsapi.AlbumInfo;
import org.superbiz.moviefun.albumsapi.AlbumFixtures;
import org.superbiz.moviefun.albumsapi.AlbumsClient;
import org.superbiz.moviefun.moviesapi.MovieFixtures;
import org.superbiz.moviefun.moviesapi.MovieInfo;
import org.superbiz.moviefun.moviesapi.MoviesClient;

import java.util.Map;

@Controller
public class HomeController {

  //  private final AlbumsBean albumsBean;
    @Autowired
    MovieFixtures movieFixtures;

    @Autowired
    AlbumFixtures albumFixtures;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    AlbumInfo albumInfo;

    private MoviesClient moviesClient;

    private AlbumsClient albumsClient;

    /*public HomeController(MoviesBean moviesBean, AlbumsBean albumsBean, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesBean = moviesBean;
        this.albumsBean = albumsBean;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }*/

    public HomeController(MoviesClient moviesClient, AlbumsClient albumsClient){
        this.moviesClient=moviesClient;
        this.albumsClient=albumsClient;
      //  this.movieInfo=movieInfo;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {


        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

       for (AlbumInfo album : albumFixtures.load()) {
           albumsClient.addAlbums(album);
        }

        model.put("movies", moviesClient.getMovies());
     //   model.put("albums", albumsBean.getAlbums());

        return "setup";
    }
}
