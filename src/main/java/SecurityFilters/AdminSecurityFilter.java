package SecurityFilters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebFilter(filterName = "AdminSecurityFilter")
public class AdminSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = httpServletRequest.getSession(false);

        if(session == null) {
            httpServletRequest.getRequestDispatcher("error.html").forward(httpServletRequest, httpServletResponse);
        }else if("user".equals(session.getAttribute("role"))){
            httpServletRequest.getRequestDispatcher("errorpage.jsp").forward(httpServletRequest, httpServletResponse);
        }else if("admin".equals(session.getAttribute("role"))){
            chain.doFilter(req, resp);
        }else{
            httpServletRequest.getRequestDispatcher("errorpage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
