package com.kuark.tool.model.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * 配置过滤器，用于过滤指定请求
 * Created by caoqingyuan on 2016/9/1.
 */
public class SystemFilter implements Filter {
    private static final Logger logger=Logger.getLogger(SystemFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("systemFilter init start ...");
        logger.info("systemFilter init finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //用于获取在web.xml中配置的指定请求，进行指定的处理后，再使用chain进行发送
        logger.info("doFilter");
//        try {
//            ThreadLocalVar.setSourceCon(AppContext.createDataSource().getConnection());
//            System.out.println("getSourceCon");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            ThreadLocalVar.unSourceCon();
//        }
        chain.doFilter(request,response);
//        System.out.println("dofilter end");
//        ThreadLocalVar.unSourceCon();
//        System.out.println("close connection finish");
//        return;
    }

    @Override
    public void destroy() {
        logger.info("systemFilter destroy finished");
    }
}
