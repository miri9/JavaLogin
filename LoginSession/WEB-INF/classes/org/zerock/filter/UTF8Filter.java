package org.zerock.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/")
public class UTF8Filter implements Filter {

    public UTF8Filter() {
    }

	public void destroy() {
	}
	// dofilter : "필터체인" 위함. ex) aup 횡단관심사분리 in. : 다른 필터로 넘어갈 수 있음. 반면에 리다이렉트 시키면 다른 필터로 안넘어가게끔 할수 있음. 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//ServletRequest/ServletResponse : http 응답/요청보다 상위의 개념. 필터에선 다운캐스팅 반드시 필요. 
		HttpServletRequest req = (HttpServletRequest) request;
		
		System.out.println("UTF8 Filter Run...");
		
		req.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
