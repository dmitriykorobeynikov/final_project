package filters;

import dao.EmployerDao;
import dao.OrderDao;
import dao.OrderForAdminDao;
import db.ConnectionPool;
import records.Employer;
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

//@WebFilter(filterName = "EditBrigadeFilter")
public class EditBrigadeFilter implements Filter {
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
        OrderForAdmin orderForAdmin = orderForAdminDao.selectByOrderId((int)session.getAttribute("order_id"));

        req.setAttribute("order",orderForAdmin);

        EmployerDao employerDao = new EmployerDao(connect);
        ArrayList<Employer> involvedEmployers = employerDao.selectInvolvedEmployers((int)session.getAttribute("order_id"));
        req.setAttribute("involvedEmployers", involvedEmployers);

        ArrayList<Employer> unInvolvedEmployers = employerDao.selectUnInvolvedEmployers((int)session.getAttribute("order_id"));
        req.setAttribute("unInvolvedEmployers", unInvolvedEmployers);
        ConnectionPool.returnConnection(connect);


        //session.removeAttribute("order_id");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
