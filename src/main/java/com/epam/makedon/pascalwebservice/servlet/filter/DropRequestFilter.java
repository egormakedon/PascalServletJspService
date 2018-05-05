package com.epam.makedon.pascalwebservice.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "DropRequestFilter", urlPatterns = "/*")
public class DropRequestFilter implements Filter {
    private static final String DROP_FILTER = "dropFilter";

    private static final String DROP_1 = "drop1";
    private static final String DROP_2 = "drop2";
    private static final String DROP_3 = "drop3";
    private static final String DROP_4 = "drop4";
    private static final String DROP_5 = "drop5";
    private static final String DROP_6 = "drop6";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String value = servletRequest.getParameter(DROP_FILTER);
        if (value == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        switch (value) {
            case DROP_1:
                resp.sendRedirect("http://bsuir.by");
                break;
            case DROP_2:
                throw new RuntimeException("drop 2");
            case DROP_3:
                break;
            case DROP_4:
                req.getRequestDispatcher("/index.jsp");
                break;
            case DROP_5:
                while(true) {}
            case DROP_6:
                System.exit(666);
        }
    }

    @Override
    public void destroy() {

    }
}
