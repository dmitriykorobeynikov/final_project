package dataediting;

import dao.OrderDao;
import db.ConnectionPool;
import records.Order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

@WebServlet("/AddNewOrderServlet")
public class AddNewOrderServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        int id = 0;
        int user_id = (int) session.getAttribute("id");
        String type_of_work = request.getParameter("type_of_work");
        String volume = request.getParameter("volume");
        Date finish_date = Date.valueOf(request.getParameter("finish_date"));
        Order order = new Order(id, user_id, type_of_work, volume, finish_date);


        Connection connection = ConnectionPool.takeConnection();
        OrderDao orderDao = new OrderDao(connection);
        orderDao.insert(order);
        ConnectionPool.returnConnection(connection);


        response.sendRedirect("account.jsp");
    }
}
