package com.epam.makedon.pascalwebservice.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/error/errorPage.jsp", "/addPage.jsp", "/index.jsp", "/Controller", "/articlePage.jsp"})
public class LastQuerySaver implements Filter {
    private static final String LAST_PAGE = "lastPage";
    private static final String COMMAND = "command";
    private static final String CHANGE_LOCALE = "change_locale";

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
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String commandValue = req.getParameter(COMMAND);
        if (commandValue != null && commandValue.equals(CHANGE_LOCALE)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String lastPage;
        if (req.getQueryString() == null) {
            lastPage = req.getServletPath();
        } else {
            lastPage = req.getServletPath() + "?" + req.getQueryString();
        }

        HttpSession session = req.getSession();
        session.setAttribute(LAST_PAGE, lastPage);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
