package dataediting;

import dao.EmployerDao;
import dao.OrderDao;
import db.ConnectionPool;
import records.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet("/EmployerEditingServlet")
public class EmployerEditingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        int order_id = (int)session.getAttribute("order_id");
        int employer_id = Integer.parseInt(request.getParameter("id"));

        Connection connect = ConnectionPool.takeConnection();
        EmployerDao employerDao = new EmployerDao(connect);

        if(request.getParameter("Add") != null){
            employerDao.insertOrderEmployer(order_id,employer_id);
        }else {
            employerDao.deleteOrderEmployer(order_id,employer_id);
        }
        ConnectionPool.returnConnection(connect);

        response.sendRedirect("editbrigade.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
