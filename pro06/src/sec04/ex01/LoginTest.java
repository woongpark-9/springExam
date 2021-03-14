package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest
 */
@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

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
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("���̵� : "+id);
		System.out.println("��й�ȣ : "+pw);
		
		if(id!= null && (id.length() !=0)) {
			StringBuffer data = new StringBuffer();
			data.append("<html> ");
			data.append("<body> ");
			data.append(id+" ��!! �α��� �ϼ̽��ϴ�. ");
			data.append("</body> ");
			data.append("</html>");
			out.print(data);
		}else {
			StringBuffer data = new StringBuffer();
			data.append("<html> ");
			data.append("<body> ");
			data.append("���̵� �Է��ϼ���!!! ");
			data.append("<br> ");
			data.append("<a href='http://localhost:8080/pro06/test01/login.html'> �α��� â���� �̵� </a> ");
			data.append("</body> ");
			data.append("</html>");
			out.print(data);
		}
	}

}
