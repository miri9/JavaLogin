package org.zerock.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.vo.MemberVO;

@WebFilter({"/sample/list","/sample/register"}) // 배열 형태 : url 체크 위함.  ex) 리스트 보거나 메시지 보낼때, 로그인한 사용자인지 확인.
public class CheckLoginFilter implements Filter {

	public CheckLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		if(session.getAttribute("member")!=null) { //세션 있음 : 통과
			chain.doFilter(request, response);
			return;

		} else { //세션없음 : 기억하기 쿠키 있는지 검사

			// 쿠키 없음
			if(req.getCookies() == null || req.getCookies().length == 0) {
				res.sendRedirect("/member/login?result=fail2");
				return;
			}

			// 쿠키 있음 : 쿠키 값을 복호화하고, 그 값으로 세션만든다.
			Optional<Cookie> result = Arrays.stream(req.getCookies())
					.filter(ck->ck.getName().equals("rememberme")).findFirst();
			if(result.isPresent()) {
				Cookie rememberCookie = result.get();
				String value = rememberCookie.getValue();
				String deValue = new String(Base64.getUrlDecoder().decode(value.getBytes()));
				
				String[] arr = deValue.split(":");
				MemberVO member = new MemberVO();
				member.setMid(arr[0]);
				member.setMname(arr[1]);
				session.setAttribute("member", member);
				
				chain.doFilter(request, response);
			}
			
		} // end if-else
	} //end doFilter

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
