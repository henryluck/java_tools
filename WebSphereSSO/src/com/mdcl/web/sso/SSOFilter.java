package com.mdcl.web.sso;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public final class SSOFilter implements Filter {
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public SSOFilter() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#init(FilterConfig arg0)
	 */
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//检查用户的Session
		
		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		
		if(session == null) {
			
			//判断是不是从Portal来的用户
			// 
			
			SSOTokenManager manager = SSOTokenManager.getInstance();
			SSOToken token = manager.createSSOToken((HttpServletRequest)request);
			
			if (manager.isValidToken(token)) {
			      java.security.Principal p = token.getPrincipal();
			      String uid = p.getName();
			      // 这个请求是合法的，从portal中来的，为这个用户SSO。
			      // TODO 为这个用户有权限吗，有权限就为他创建Session
			      
			      
			      
			} else {
				// 这个请求不是合法的，需要他认证
				
				// TODO 把这个页面转到login的页面上去
				
			}
		}
		
			
		
		chain.doFilter(request,response);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}