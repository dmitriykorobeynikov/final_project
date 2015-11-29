package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


import dao.OrderDao;
import db.ConnectionPool;
import dao.UserDao;
import records.Order;
import records.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;
        try{
            Connection connect = ConnectionPool.takeConnection();
            UserDao userDao = new UserDao(connect);
            user = userDao.getByLoginAndPassword(login, password);
            ConnectionPool.returnConnection(connect);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user != null){
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30*60);

            session.setAttribute("id",user.getId());
            session.setAttribute("login",user.getLogin());
            session.setAttribute("role",user.getRole());


            //System.out.println("LoginServlet " + session.getAttribute("local"));

            if(session.getAttribute("role").equals("user")){
                response.sendRedirect("account.jsp");
            }else if(session.getAttribute("role").equals("admin")){
                response.sendRedirect("dispatcher.jsp");
            }
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();

            ResourceBundle res = ResourceBundle.getBundle("localization", new Locale((String) request.getSession(true).getAttribute("local")) );
            out.println("<font color=red> "+ res.getString("authorization.error.message")+"</font><br>");
            rd.include(request,response);
            out.close();
        }
    }
}