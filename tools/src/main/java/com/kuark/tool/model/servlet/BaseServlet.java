package com.kuark.tool.model.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这是servlet最基本的使用方式，要想使请求被这个类处理到，必须再web.xml中进行该类路径的设置
 * client通过访问该路径，就可以把请求传给该类进行处理。
 * 这是在不适用框架的基础上进行的servlet请求。目前有springmvc和struts2框架进行web请求的封装
 * Created by caoqingyuan on 2016/9/1.
 */
public class BaseServlet extends HttpServlet {
    //序列化的版本号(通过这种方式来解决不同的软件版本之间的串行话问题)
    private static final long serialVersionUID = 3075928223335310013L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("hhhhhhh");
    }
}
