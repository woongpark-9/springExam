package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest5
 */
//@WebServlet("/login")
public class SessionTest5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTest5() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String	user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		if(session.isNew()) {
			if(user_id != null) {
				session.setAttribute("user_id", user_id);
				String url = response.encodeURL("login");
				out.println("<a href="+url+">�α��� ���� Ȯ��</a>");
			}else {
			
			
				out.print("<a href='login2.html'>�ٽ÷α��� �ϼ���!!</a>");
				session.invalidate();
			}
		}else {
			user_id = (String) session.getAttribute("user_id");
			if(user_id != null && user_id.length() != 0) {
				out.print("�ȳ��ϼ��� " +user_id+"�� !!!");
			}else {
				out.print("<a href='login2.html'>�ٽ� �α����ϼ���!!</a>");
				session.invalidate();
			}
		}
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
