package com.epam.makedon.pascalwebservice.dao.article;

import com.epam.makedon.pascalwebservice.dao.DaoException;
import com.epam.makedon.pascalwebservice.dao.DaoRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao {
    Logger LOGGER = LoggerFactory.getLogger(Dao.class);

    default void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("", e);
                throw new DaoRuntimeException(e);
            }
        }
    }

    default void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("", e);
                throw new DaoRuntimeException(e);
            }
        }
    }

    boolean updateArticle(Article article) throws DaoException;
    boolean deleteArticle(Article article) throws DaoException;
    boolean addArticle(Article article) throws DaoException;
    List<Article> takeArticleList() throws DaoException;
    Article takeArticle(String title) throws DaoException;
}
