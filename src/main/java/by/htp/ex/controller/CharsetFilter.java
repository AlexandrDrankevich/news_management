package by.htp.ex.controller;

import jakarta.servlet.*;

import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;
    private String initParameterEncoding="characterEncoding";

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(initParameterEncoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
