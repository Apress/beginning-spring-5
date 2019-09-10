package com.bsg5.chapter5;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class XMLContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context =
                buildXmlContext(event.getServletContext());
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildXmlContext(ServletContext sc) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setServletContext(sc);
        context.refresh();
        return context;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
