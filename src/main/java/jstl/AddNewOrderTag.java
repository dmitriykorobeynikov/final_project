package jstl;

import org.apache.log4j.Logger;
import records.Order;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class AddNewOrderTag extends TagSupport {
    private static final Logger log = Logger.getLogger(AddNewOrderTag.class);
    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    public int doStartTag(){
        ResourceBundle res = ResourceBundle.getBundle("localization", new Locale(local) );

        try(JspWriter out = pageContext.getOut()) {
            out.write("<form action = \"AddNewOrderServlet\" method = \"post\">");
            out.write("<input type = \"hidden\" name = \"id\" value = \"\">");
            out.write("<input name = \"type_of_work\" placeholder = \""+res.getString("account.orders.type_of_work")+"\" type = \"text\" value = \"\"><br>");
            out.write("<input name = \"volume\"       placeholder = \""+res.getString("account.orders.volume")+"\" type = \"text\" value = \"\"><br>");
            out.write("<input name = \"finish_date\" type = \"date\" value = \"\"><br>");
            out.write("<input type = \"submit\" value=\""+res.getString("save")+"\" name=\"save\"></td>");
            out.write("</form>");
        }
        catch (IOException e) {
            log.error("Can't get pageContext",e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag(){
        return SKIP_PAGE;
    }
}
