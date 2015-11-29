package dataediting;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/adminOrdersEditingServlet")
public class AdminOrdersEditingServlet  extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String saveBut = req.getParameter("save");
        String delBut = req.getParameter("delete");

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int price = Integer.parseInt(req.getParameter("price"));
        String publisher = req.getParameter("publisher");
        String description = req.getParameter("desc");
        String issn = req.getParameter("issn");

        //Periodical per = new Periodical(id, title, price, publisher, description, issn);


        if (delBut != null){
            //Change.delete(per);
        }
        if (saveBut != null) {
            //Change.update(per);
        }

        resp.sendRedirect("admin_page.jsp");
    }
}
