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
		//����û���Session
		
		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		
		if(session == null) {
			
			//�ж��ǲ��Ǵ�Portal�����û�
			// 
			
			SSOTokenManager manager = SSOTokenManager.getInstance();
			SSOToken token = manager.createSSOToken((HttpServletRequest)request);
			
			if (manager.isValidToken(token)) {
			      java.security.Principal p = token.getPrincipal();
			      String uid = p.getName();
			      // ��������ǺϷ��ģ���portal�����ģ�Ϊ����û�SSO��
			      // TODO Ϊ����û���Ȩ������Ȩ�޾�Ϊ������Session
			      
			      
			      
			} else {
				// ��������ǺϷ��ģ���Ҫ����֤
				
				// TODO �����ҳ��ת��login��ҳ����ȥ
				
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