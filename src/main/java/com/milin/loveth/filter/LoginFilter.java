package com.milin.loveth.filter;

import com.google.common.collect.Lists;
import com.milin.loveth.models.Constants;
import com.milin.loveth.models.User;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Milin on 2016/11/9.
 */
@Component
@Configuration
public class LoginFilter implements Filter {

    public void destroy() {
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(LoginFilter loginFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(loginFilter);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList(
                "/manage.html", "/images/fall.html"
        ));
        return filterRegistrationBean;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(Constants.SESSION_USER);
        if (sessionUser == null) {
            response.sendRedirect(request.getContextPath()
                    + "/toLogin.html");
            return;
        }
        chain.doFilter(req, res);
    }

}