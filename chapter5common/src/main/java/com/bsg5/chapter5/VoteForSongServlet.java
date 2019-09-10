package com.bsg5.chapter5;

import com.bsg5.chapter3.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/vote")
public class VoteForSongServlet extends HttpServlet {
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ApplicationContext context = (ApplicationContext) req
                .getServletContext()
                .getAttribute("context");
        MusicService service = context.getBean(MusicService.class);
        ObjectMapper mapper = new ObjectMapper();

        String artist = req.getParameter("artist");
        String song = req.getParameter("song");

        if (artist == null || song == null) {
            log("Missing data in request: requires artist and song parameters");
            resp.setStatus(500);
        } else {
            log("Voting for artist " + artist + ", song " + song);
            service.voteForSong(artist, song);

            resp.setStatus(200);
            resp.getWriter().println(
                    mapper.writeValueAsString(service.getSong(artist, song))
            );
        }
    }
}
