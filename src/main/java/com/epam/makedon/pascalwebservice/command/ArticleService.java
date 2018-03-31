package com.epam.makedon.pascalwebservice.command;

import com.epam.makedon.pascalwebservice.dao.DaoException;
import com.epam.makedon.pascalwebservice.dao.article.Article;
import com.epam.makedon.pascalwebservice.dao.article.ArticleDao;
import com.epam.makedon.pascalwebservice.servlet.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ArticleService implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    private static final String COMMAND_TYPE = "command_type";
    private static final String UPDATE_RU = "Изменить";
    private static final String UPDATE_EN = "Update";

    private static final String ID = "id";

    @Override
    public Router execute(HttpServletRequest req) {
        String commandType = req.getParameter(COMMAND_TYPE);

        Router router = new Router();
        router.setRedirect();

        if (commandType.equals(UPDATE_RU) || commandType.equals(UPDATE_EN)) {
            //
        } else {
            String id = req.getParameter(ID);
            Article article = new Article();
            article.setArticleId(Integer.valueOf(id));
            try {
                boolean result = ArticleDao.getInstance().deleteArticle(article);
                String answer;
                if (result) {
                    answer = "article " + title + " removed successfully";
                } else {
                    answer = "article " + title + " wrong removed";
                }
                router.setPath(Page.INDEX.getPath() + "?answer=" + answer);
            } catch (DaoException e) {
                LOGGER.error("", e);
                router.setPath(Page.INDEX.getPath() + "?answer=" + e.getMessage());
            }
        }

        return router;
    }
}