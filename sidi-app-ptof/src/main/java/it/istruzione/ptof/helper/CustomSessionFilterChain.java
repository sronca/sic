package it.istruzione.ptof.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomSessionFilterChain implements Filter {

	private final String SESSION_OBJ_MONITOR ="SESSION_OBJ_MONITOR";
	
	public CustomSessionFilterChain() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response , FilterChain chain)
			throws IOException, ServletException {
		
		String path = ((HttpServletRequest) request).getRequestURI();
		String context = ((HttpServletRequest) request).getContextPath();
		if (path.startsWith(context+"/home.do")) {
			 chain.doFilter(request, response);
			 HttpSession session = ((HttpServletRequest)request).getSession();
			 session.setAttribute(SESSION_OBJ_MONITOR ,SESSION_OBJ_MONITOR);
		} else {
			HttpSession session = ((HttpServletRequest)request).getSession(false);// don't create if it doesn't exist
			if(  session != null &&  !session.isNew() && session.getAttribute(SESSION_OBJ_MONITOR)!=null   ) {
			    chain.doFilter(request, response);
			} else {
				if ( path.indexOf(".json")>0){
				    ((HttpServletResponse)response).setStatus(408);
				} else {
					((HttpServletResponse)response).sendRedirect(context+"/home.do");
				}
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	 
	}

}
