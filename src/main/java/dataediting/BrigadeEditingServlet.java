package dataediting;

import dao.EmployerDao;
import db.ConnectionPool;
import records.Employer;
import records.OrderForAdmin;

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
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/BrigadeEditingServlet")
public class BrigadeEditingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

//        HttpSession session = request.getSession(false);
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        int user_id = Integer.parseInt(request.getParameter("user_id"));
//        String type_of_work = request.getParameter("type_of_work");
//        String volume = request.getParameter("volume");
//        Date finish_date = Date.valueOf(request.getParameter("finish_date"));
//        String apartment = request.getParameter("apartment");
//
//
//
//        request.setAttribute("order",new OrderForAdmin(id,user_id,type_of_work,volume,finish_date,apartment));
//
//        Connection connect = ConnectionPool.takeConnection();
//        EmployerDao employerDao = new EmployerDao(connect);
//        ArrayList<Employer> involvedEmployers = employerDao.selectInvolvedEmployers(id);
//        request.setAttribute("involvedEmployers",involvedEmployers);
//        ConnectionPool.returnConnection(connect);
//        getServletContext().getRequestDispatcher("/editbrigade.jsp").include(request,response);


        HttpSession session = request.getSession(false);

        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String type_of_work = request.getParameter("type_of_work");
        String volume = request.getParameter("volume");
        Date finish_date = Date.valueOf(request.getParameter("finish_date"));
        String apartment = request.getParameter("apartment");

        //System.out.println(apartment);

        session.setAttribute("order_id",id);

        //request.setAttribute("order",new OrderForAdmin(id,user_id,type_of_work,volume,finish_date,apartment));

        //getServletContext().getRequestDispatcher("/editbrigade.jsp").include(request,response);
        response.sendRedirect("editbrigade.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
