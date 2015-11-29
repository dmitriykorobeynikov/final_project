package dataediting;


import dao.OrderDao;
import db.ConnectionPool;
import records.Order;

import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;

@WebServlet(value = "/userOrdersEditingServlet")
public class UserOrdersEditingServlet extends HttpServlet {
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = (int) session.getAttribute("id");
        String type_of_work = request.getParameter("type_of_work");
        String volume = request.getParameter("volume");
        Date finish_date = Date.valueOf(request.getParameter("finish_date"));
        Order order = new Order(id, user_id, type_of_work, volume, finish_date);


        Connection connection = ConnectionPool.takeConnection();
        OrderDao orderDao = new OrderDao(connection);
        if (request.getParameter("save") != null) {
            orderDao.update(order);
        }else if (request.getParameter("delete") != null){
            orderDao.delete(order);
        }

        ConnectionPool.returnConnection(connection);
        response.sendRedirect("account.jsp");
    }

}
