package filters;

import dao.OrderDao;
import db.ConnectionPool;
import records.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

//@WebFilter("/account.jsp")
public class AccountFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        HttpSession session = httpServletRequest.getSession(false);

        Connection connect = ConnectionPool.takeConnection();
        OrderDao orderDao = new OrderDao(connect);

        ArrayList<Order> orders = orderDao.selectByUserId((int)session.getAttribute("id"));
        httpServletRequest.setAttribute("orders",orders);
        ConnectionPool.returnConnection(connect);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
