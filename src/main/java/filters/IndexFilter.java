package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;


@WebFilter(urlPatterns = "/index.jsp")
public class IndexFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = httpServletRequest.getSession(true);
        if(session.getAttribute("local") == null) {
            session.setAttribute("local", "ru");
            //session.setAttribute("local", Locale.getDefault());
            //System.out.println(Locale.getDefault());
        }


        if(session == null) {
            chain.doFilter(req, resp);
        }else if("user".equals(session.getAttribute("role"))){
            httpServletResponse.sendRedirect("account.jsp");
        }else if("admin".equals(session.getAttribute("role"))){
            httpServletResponse.sendRedirect("dispatcher.jsp");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
