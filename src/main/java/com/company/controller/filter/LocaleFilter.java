
package com.company.controller.filter;

import com.company.config.HospitalConfig;
import com.company.i18n.SupportedLocale;
import com.company.view.Attributes;
import com.company.view.Parameters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) request);
        HttpSession session = req.getSession();

        if (req.getSession().getAttribute(Attributes.BUNDLE_FILE) == null) {
            req.getSession().setAttribute(Attributes.BUNDLE_FILE, HospitalConfig.MESSAGES);
        }

        changeUserLocaleByRequestParameter(req);

        if (session.getAttribute(Attributes.USER_LOCALE) == null) {
            setupUserLocale(req);
        }

        chain.doFilter(request, response);
    }

    private void changeUserLocaleByRequestParameter(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (request.getParameter(Parameters.USER_LOCALE) != null) {
            Locale locale = SupportedLocale.getDefault();
            for (SupportedLocale loc : SupportedLocale.values()) {
                if (loc.getParam().equals(request.getParameter(Parameters.USER_LOCALE))) {
                    locale = loc.getLocale();
                    break;
                }
            }
            session.setAttribute(Attributes.USER_LOCALE, locale);
        }
    }

    private void setupUserLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = null;
        Locale requestLocale = request.getLocale();
        if (requestLocale != null) {
            for (SupportedLocale loc : SupportedLocale.values()) {
                if (loc.getLocale().toString().equals(requestLocale.toString())) {
                    locale = loc.getLocale();
                    break;
                }
            }
        }
        if (locale == null) {
            locale = SupportedLocale.getDefault();
        }
        session.setAttribute(Attributes.USER_LOCALE, locale);
    }

    @Override
    public void destroy() {

    }
}

