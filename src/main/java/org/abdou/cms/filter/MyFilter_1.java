package org.abdou.cms.filter;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
@Component
@Order(3)
public class MyFilter_1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("filter1 is called");
        filterChain.doFilter(servletRequest,servletResponse);
    }


}
