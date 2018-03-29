package com.epam.makedon.pascalwebservice.servlet;

import com.epam.makedon.pascalwebservice.command.Command;
import com.epam.makedon.pascalwebservice.command.Factory;
import com.epam.makedon.pascalwebservice.dao.ConnectionPool;
import com.epam.makedon.pascalwebservice.dao.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(displayName = "PascalWebServlet", urlPatterns = "/Controller", name = "PascalWebServlet", loadOnStartup = 0)
public class PascalWebServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(PascalWebServlet.class);
    private static final String COMMAND = "command";

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
        LOGGER.info("init connection pool");
        LOGGER.info("init pascal web servlet");
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (DaoException e) {
            LOGGER.error("", e);
            throw new ServletRuntimeException(e);
        }
        LOGGER.info("destroy connection pool");
        LOGGER.info("destroy pascal web servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Command> commandOptional = Factory.defineCommand(req.getParameter(COMMAND));
        if (!commandOptional.isPresent()) {
            LOGGER.error("command doesn't exist");
            throw new ServletRuntimeException("command doesn't exist");
        }

        Command command = commandOptional.get();
        Router router = command.execute(req);
        String path = router.getPath();
        if (router.getRoute() == Router.RouteType.FORWARD) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(path);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(path);
        }
    }
}
