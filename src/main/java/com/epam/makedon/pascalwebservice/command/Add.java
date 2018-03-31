package com.epam.makedon.pascalwebservice.command;

import com.epam.makedon.pascalwebservice.dao.DaoException;
import com.epam.makedon.pascalwebservice.dao.article.Article;
import com.epam.makedon.pascalwebservice.dao.article.ArticleDao;
import com.epam.makedon.pascalwebservice.servlet.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class Add implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(Add.class);
    private static final String TITLE = "title";
    private static final String BODY = "body";

    @Override
    public Router execute(HttpServletRequest req) {
        String title = req.getParameter(TITLE);
        String body = req.getParameter(BODY);
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);

        Router router = new Router();
        router.setRedirect();

        try {
            boolean result = ArticleDao.getInstance().addArticle(article);
            String answer;
            if (result) {
                answer = title + " added successfully";
            } else {
                answer = title + " wrong added";
            }
            router.setPath(Page.ADD.getPath() + "?answer=" + answer);
        } catch (DaoException e) {
            LOGGER.error("", e);
            router.setPath(Page.ADD.getPath() + "?answer=" + e.getMessage());
        }

        return router;
    }
}
