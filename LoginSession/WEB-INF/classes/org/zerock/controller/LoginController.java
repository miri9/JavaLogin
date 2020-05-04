package org.zerock.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
		.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		if(mid.equals("u1")&&mpw.equals("u1")) { //로그인 성공
			//MemberVO 를 세션에 담는다.
			MemberVO vo = new MemberVO();
			vo.setMid(mid);
			vo.setMpw(mpw);
			vo.setMname("홍길동");
			
			HttpSession session = request.getSession(); //세션쿠키는 서버에서 발행하므로, 요청 객체로부터 구할 수 있다.
			session.setAttribute("member", vo);
			
			response.sendRedirect("/sample/list"); //컨트롤러 경로로 보낸다.
			
			return;
		} else { // 로그인 실패 : 로그인 페이지로 넘긴다
			response.sendRedirect("/member/login?result=fail");
			
		}
	}

}
