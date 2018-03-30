package com.epam.makedon.pascalwebservice.servlet.listener;

import com.epam.makedon.pascalwebservice.command.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCreater implements HttpSessionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCreater.class);

    private static final String LANG = "lang";
    private static final String EN = "en";
    private static final String LAST_PAGE = "lastPage";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(LANG, EN);
        session.setAttribute(LAST_PAGE, Page.INDEX.getPath());
        LOGGER.info(session.getId() + " session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        LOGGER.info(session.getId() + " session destroyed");
    }
}
