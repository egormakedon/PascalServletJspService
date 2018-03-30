package com.epam.makedon.pascalwebservice.servlet.filter;

import com.epam.makedon.pascalwebservice.dao.DaoException;
import com.epam.makedon.pascalwebservice.dao.DaoRuntimeException;
import com.epam.makedon.pascalwebservice.dao.article.Article;
import com.epam.makedon.pascalwebservice.dao.article.ArticleDao;
import com.epam.makedon.pascalwebservice.dao.article.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/index.jsp")
public class TakeTitles implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TakeTitles.class);

    private static final String TITLE_LIST = "titleList";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void destroy() {
        //
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Dao dao = ArticleDao.getInstance();
        List<Article> articleList;
        try {
            articleList = dao.takeArticleList();
        } catch (DaoException e) {
            LOGGER.error("", e);
            throw new DaoRuntimeException(e);
        }
        List<String> titleList = new ArrayList<>();
        for (Article article : articleList) {
            titleList.add(article.getTitle());
        }
        servletRequest.setAttribute(TITLE_LIST, titleList);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
