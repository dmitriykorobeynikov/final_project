package jstl;

import org.apache.log4j.Logger;
import records.Order;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserOrderTag extends SimpleTagSupport {
    private static final Logger log = Logger.getLogger(UserOrderTag.class);

    private Order order;
    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void doTag() throws JspException, IOException {
        ResourceBundle res = ResourceBundle.getBundle("localization", new Locale(local) );

        try(JspWriter out = getJspContext().getOut()) {
            out.write("<form action = \"userOrdersEditingServlet\" method = \"post\">");
            out.write("<tr>");
            out.write("<input name = \"id\"      type = \"hidden\"  value = \"" + order.getId() + "\"");
            out.write("<input name = \"user_id\" type = \"hidden\" value = \"" + order.getUser_id() + "\">");
            out.write("<td> <input name = \"type_of_work\" type = \"text\" value = \"" + order.getType_of_work() + "\"></td>");
            out.write("<td> <input name = \"volume\" type = \"text\" value = \"" + order.getVolume() + "\"></td>");
            out.write("<td> <input name = \"finish_date\" type = \"date\" value = \"" + order.getFinish_date() + "\"></td>");
            out.write("<td><input type = \"submit\" value=\"" + res.getString("save") + "\" name=\"save\"></td>");
            out.write("<td><input type = \"submit\" value=\"" + res.getString("delete") + "\" name=\"delete\"></td>");
            out.write("</tr></form>");
        }catch (IOException e) {
            log.error("Can't get pageContext",e);
        }
    }


}