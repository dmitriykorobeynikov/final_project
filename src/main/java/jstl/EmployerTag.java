package jstl;

import records.Employer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployerTag extends SimpleTagSupport {
    private Employer employer;
    private boolean involved;
    private String local;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public void doTag() throws JspException, IOException {
        ResourceBundle res = ResourceBundle.getBundle("localization", new Locale(local) );

        JspWriter out = getJspContext().getOut();
        out.write("<form action = \"EmployerEditingServlet\" method = \"post\">");
        out.write("<input type = \"hidden\" name = \"id\" value = \"" + employer.getId() + "\">");
        //out.write("<input type = \"hidden\" name = \"user_id\" value = \"" + orders.get(i).getUser_id() + "\">");
        out.write("<tr>");
        out.write("<td> <input name = \"name\" type = \"text\" value = \"" + employer.getName() + "\"readonly></td>");
        out.write("<td> <input name = \"profession\" type = \"text\" value = \"" + employer.getProfession()+ "\"readonly></td>");
        if(involved)
            out.write("<td><input type = \"submit\" name=\"Remove\" value=\""+res.getString("remove")+"\"></td>");
        else
            out.write("<td><input type = \"submit\" name=\"Add\" value=\""+res.getString("add")+"\"></td>");
        out.write("</tr></form>");
    }


    public boolean isInvolved() {
        return involved;
    }

    public void setInvolved(boolean involved) {
        this.involved = involved;
    }
}
