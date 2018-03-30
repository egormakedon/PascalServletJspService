package com.epam.makedon.pascalwebservice.command;

import com.epam.makedon.pascalwebservice.servlet.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocale implements Command {
    private static final String LANG = "lang";
    private static final String LAST_PAGE = "lastPage";

    @Override
    public Router execute(HttpServletRequest req) {
        String locale = req.getParameter(LANG);

        HttpSession session = req.getSession();
        session.setAttribute(LANG, locale);
        String lastPage = (String) session.getAttribute(LAST_PAGE);

        Router router = new Router();
        router.setRedirect();
        router.setPath(lastPage);
        return router;
    }
}