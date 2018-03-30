package com.epam.makedon.pascalwebservice.command;

import com.epam.makedon.pascalwebservice.dao.DaoException;
import com.epam.makedon.pascalwebservice.dao.DaoRuntimeException;
import com.epam.makedon.pascalwebservice.dao.article.Article;
import com.epam.makedon.pascalwebservice.dao.article.ArticleDao;
import com.epam.makedon.pascalwebservice.servlet.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class TakeArticle implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(TakeArticle.class);

    private static final String TITLE = "title";
    private static final String ARTICLE = "article";

    @Override
    public Router execute(HttpServletRequest req) {
        String title = req.getParameter(TITLE);

        try {
            Article article = ArticleDao.getInstance().takeArticle(title);
            req.setAttribute(ARTICLE, article);
            Router router = new Router();
            router.setForward();
            router.setPath(Page.ARTICLE.getPath());
            return router;
        } catch (DaoException e) {
            LOGGER.error("", e);
            throw new DaoRuntimeException(e);
        }
    }
}
