package servlets;

import db.ConnectionPool;
import dao.UserDao;
import records.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setAddress(address);
        user.setRole("user");


        Connection connection = ConnectionPool.takeConnection();
        UserDao userDao = new UserDao(connection);
        boolean success = userDao.insert(user);
        ConnectionPool.returnConnection(connection);

        if(success) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30*60);

            user = userDao.getByLoginAndPassword(login, password);

            session.setAttribute("id",user.getId());
            session.setAttribute("login",user.getLogin());
            session.setAttribute("role",user.getRole());

            if(session.getAttribute("role").equals("user")){
                response.sendRedirect("account.jsp");
            }else if(session.getAttribute("role").equals("admin")){
                response.sendRedirect("dispatcher.jsp");
            }

        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registration.jsp");
            PrintWriter out = response.getWriter();
            ResourceBundle res = ResourceBundle.getBundle("localization", new Locale((String) request.getSession(true).getAttribute("local")) );

            out.println("<font color=red>" + res.getString("registration.error.message") +"</font><br>");
            rd.include(request,response);
            out.close();
        }
    }
}
