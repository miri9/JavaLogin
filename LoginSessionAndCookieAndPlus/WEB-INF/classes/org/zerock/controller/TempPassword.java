package org.zerock.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/temp")
public class TempPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TempPassword() {
        super();
    }
    //AWT : 그래픽환경 지원되는 os에서만 작동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		/*검은 박스*/
		response.setContentType("image/gif");
		BufferedImage image = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
		
		/*배경*/
		Graphics g = image.getGraphics();
		g.setColor(new Color(56,172,255));
		g.fillRect(0, 0, 300, 200);
		
		/*텍스트*/
		int value = (int)(Math.random()*10000);
		if(value<999) value+=1000;
		session.setAttribute("temp", ""+value); //후에 문자열 비교 위해, 문자열로 입력
		g.setColor(Color.BLACK);
		
		Font f = new Font("Consolas",Font.BOLD,100);
		g.setFont(f);
		g.drawString(""+value,10,100);
		/*출력*/
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "gif", os);
	}

}
