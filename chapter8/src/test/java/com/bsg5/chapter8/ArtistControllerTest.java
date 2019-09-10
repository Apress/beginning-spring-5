package com.bsg5.chapter8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.List;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistControllerTest extends AbstractTestNGSpringContextTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    String encode(Object data) {
        return UriUtils.encode(data.toString(),
            Charset.defaultCharset());
    }

    @DataProvider
    Object[][] artistData() {
        return new Object[][]{
            new Object[]{1, "Threadbare Loaf"},
            new Object[]{2, "Therapy Zeppelin"},
            new Object[]{3, "Clancy in Silt"},
            new Object[]{-1, null},
            new Object[]{-1, "Not A Band"}

        };
    }

    @Test(dataProvider = "artistData")
    public void testGetArtist(int id, String name) {
        String url = "http://localhost:" + port + "/artists/" + id;
        ResponseEntity<Artist> response =
            restTemplate.getForEntity(url, Artist.class);
        if (id != -1) {
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Artist artist = response.getBody();
            Artist data = new Artist(id, name);

            assertEquals(artist.getId(), data.getId());
            // note: the corrected service returns the *proper* name
            assertEquals(
                artist.getName().toLowerCase(),
                data.getName().toLowerCase()
            );
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test(dataProvider = "artistData")
    public void testSearchForArtist(int id, String name) {
        String url = "http://localhost:" + port + "/artists/search/" +
            (name != null ? encode(name) : "");
        ResponseEntity<Artist> response =
            restTemplate.getForEntity(url, Artist.class);
        if (name != null) {
            if (id == -1) {
                assertEquals(response.getStatusCode(),
                    HttpStatus.NOT_FOUND);
            } else {
                assertEquals(response.getStatusCode(), HttpStatus.OK);
                Artist artist = response.getBody();
                Artist data = new Artist(id, name);

                assertEquals(artist.getId(), data.getId());
                // note: the corrected service returns the *proper* name
                assertEquals(
                    artist.getName().toLowerCase(),
                    data.getName().toLowerCase()
                );
            }
        } else {
            assertEquals(
                response.getStatusCode(),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    @Test
    public void testSaveExistingArtist() {
        String url = "http://localhost:" + port + "/artists";
        Artist newArtist = restTemplate.getForObject(url + "/1", Artist.class);

        ResponseEntity<Artist> response =
            restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist artist = response.getBody();
        assertNotNull(artist);

        int id = artist.getId();
        assertEquals(id, newArtist.getId().intValue());
        assertEquals(artist.getName(), newArtist.getName());

        response = restTemplate.getForEntity(url + "/" + id, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }

    @DataProvider
    public Object[][] artistSearches() {
        return new Object[][]{
            new Object[]{"", 3},
            new Object[]{"T", 2},
            new Object[]{"Th", 2},
            new Object[]{"Thr", 1},
            new Object[]{"C", 1},
            new Object[]{"Z", 0}
        };
    }

    @Test(dataProvider = "artistSearches")
    public void testSearches(String name, int count) {
        ParameterizedTypeReference<List<Artist>> type =
            new ParameterizedTypeReference<>() {
            };
        String url = "/artists/match/" + encode(name);
        ResponseEntity<List<Artist>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            type
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Artist> artists = response.getBody();
        assertNotNull(artists);
        assertEquals(artists.size(), count);
    }

    // We need this to run AFTER testSearches completes, because
    // testSearches adds to the artist list and therefore we
    // might get one more artist than we're expecting out of
    // some searches.
    @Test(dependsOnMethods = "testSearches")
    public void testSaveArtist() {
        String url = "http://localhost:" + port + "/artists";
        Artist newArtist = new Artist(0, "The Broken Keyboards");

        ResponseEntity<Artist> response = restTemplate.postForEntity(
            url,
            newArtist,
            Artist.class
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Artist artist = response.getBody();
        assertNotNull(artist);
        int id = artist.getId();
        assertNotEquals(id, 0);
        assertEquals(artist.getName(), newArtist.getName());

        response = restTemplate.getForEntity(url + "/" + id, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }
}
