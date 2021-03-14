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
 * Servlet implementation class LoginTest
 */
//@WebServlet("/login")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String user_name = request.getParameter("user_name");
		String user_pw = request.getParameter("user_pw");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		LoginImpl loginUser = new LoginImpl(user_name,user_pw);
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
		}
		out.println("<head>");
		out.println("<script type='text/javascript'>");
		out.println("setTimeout('history.go(0);',5000");
		out.println("</script");
		out.println("</head>");
		out.println("<html><body>");
		out.println("아이디는 "+loginUser.user_id + "<br>");
		out.println("총 접속자수는"+LoginImpl.total_user +"<br>");
		out.println("</body></html>");
		
		
		// TODO Auto-generated method stub
	}

}
