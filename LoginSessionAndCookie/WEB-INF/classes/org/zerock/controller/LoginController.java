package org.zerock.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.vo.MemberVO;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*미구현 : 로그인 상태일 경우 다른 페이지로 넘긴다. (세션 검사)*/
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
		.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		if(mid.equals("u1")&&mpw.equals("u1")) { //로그인 성공
			/*세션 제작 : MemberVO*/
			MemberVO vo = new MemberVO();
			vo.setMid(mid);
			vo.setMpw(mpw);
			vo.setMname("홍길동");
			
			/* 로그인 기억하기 */
			String rememberme = request.getParameter("rememberme");
			System.out.println(rememberme);
			if(rememberme!=null && rememberme.equals("on")) {
				// 아이디,이름 값을 변형시키고, 쿠키에 저장한다.
				String str = vo.getMid()+":"+vo.getMname();
				String value = new String(Base64.getUrlEncoder().encode(str.getBytes()));
				System.out.println(value);
				
				Cookie rememberMeCookie = new Cookie("rememberme",value);
				rememberMeCookie.setMaxAge(60*60*24*7); //일주일 동안 유지
				rememberMeCookie.setPath("/sample"); // The cookie is visible to all the pages "in the directory you specify"
				response.addCookie(rememberMeCookie); // 로그인 후 요청 헤더-Cookie 에서 확인 가능
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("member", vo);
			
			response.sendRedirect("/sample/list"); //컨트롤러 경로로 보낸다.
			
			return;
		} else { // 로그인 실패 : 로그인 페이지로 넘긴다
			response.sendRedirect("/member/login?result=fail");
			
		}
	}

}
