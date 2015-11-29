package filters;

import dao.OrderDao;
import dao.OrderForAdminDao;
import db.ConnectionPool;
import records.Order;
import records.OrderForAdmin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

//@WebFilter("/dispatcher.jsp")
public class DispatcherFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = httpServletRequest.getSession(false);

        Connection connect = ConnectionPool.takeConnection();
        OrderForAdminDao orderForAdminDao = new OrderForAdminDao(connect);
        ArrayList<OrderForAdmin> orders = orderForAdminDao.selectAll();
        httpServletRequest.setAttribute("orders",orders);
        ConnectionPool.returnConnection(connect);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
