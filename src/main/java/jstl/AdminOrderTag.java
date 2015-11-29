package jstl;


import records.OrderForAdmin;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class AdminOrderTag extends SimpleTagSupport {
    private OrderForAdmin order;
    private boolean button=true;
    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public OrderForAdmin getOrder() {
        return order;
    }

    public void setOrder(OrderForAdmin order) {
        this.order = order;
    }


    public void doTag() throws JspException, IOException {
        ResourceBundle res = ResourceBundle.getBundle("localization", new Locale(local) );
        JspWriter out = getJspContext().getOut();

        out.write("<form action = \"BrigadeEditingServlet\" method = \"post\">");
        out.write("<input type = \"hidden\" name = \"id\" value = \""+order.getId()+"\">");
        out.write("<input type = \"hidden\" name = \"user_id\" value = \""+order.getUser_id()+"\">");

        out.write("<tr>");
        out.write("<td> <input name = \"apartment\" type = \"text\" value = \""+order.getApartment()+"\"readonly></td>");
        out.write("<td> <input name = \"type_of_work\" type = \"text\" value = \""+order.getType_of_work()+"\"readonly></td>");
        out.write("<td> <input name = \"volume\" type = \"text\" value = \""+order.getVolume()+"\"readonly></td>");
        out.write("<td> <input name = \"finish_date\" type = \"date\" value = \""+order.getFinish_date()+"\"readonly></td>");
        if (button)
            out.write("<td><input type = \"submit\" value=\""+res.getString("form_a_brigade")+"\"></td>");
        out.write("</tr></form>");
    }


    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }
}

