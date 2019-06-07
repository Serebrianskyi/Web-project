
package com.company.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("/rest/*")
public class EncodingFilter implements Filter {
    private String encodingString;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String codeRequest = servletRequest.getCharacterEncoding();
        if (!encodingString.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(encodingString);
            servletResponse.setCharacterEncoding(encodingString);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encodingString = filterConfig.getInitParameter("encoding");
        if (encodingString == null) encodingString = "UTF-8";
    }


    @Override
    public void destroy() {
        encodingString = null;

    }
}

