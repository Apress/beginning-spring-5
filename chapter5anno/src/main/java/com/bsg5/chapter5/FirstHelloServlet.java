package com.bsg5.chapter5;

import org.jtwig.web.servlet.JtwigRenderer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello1")
public class FirstHelloServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4427011101374936594L;
	private final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        renderer.dispatcherFor("/WEB-INF/templates/jtwig/hello.jtwig.html")
                .with("name", "world")
                .render(request, response);
    }
}
