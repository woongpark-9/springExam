package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest2
 */
@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginTest2() {
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
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("���̵� : "+id);
		System.out.println("�н����� : "+pw);
		if(id!= null &&(id.length() != 0)) {
			if(id.equals("admin")) {
				out.print("<html>");
				out.print("<body>");
				out.print("<font size='12'>�����ڷ� �α��� �ϼ̽��ϴ�!! </font>");
				out.print("<br>");
				out.print("<input type='button' value='ȸ������ �����ϱ�' />");
				out.print("<input type='button' value='ȸ������ �����ϱ�' />");
				out.print("</html>");
				out.print("</body>");
			}else {
				out.print("<html>");
				out.print("<body>");
				out.print(id +" �� !! �α��� �ϼ̽��ϴ�.");
				out.print("</html>");
				out.print("</body>");
			}
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("ID�� ��й�ȣ�� �Է��ϼ���!!!");
			out.print("<br>");
			out.print("<a href='http://localhost:8080/pro06/test01/login.html'> �α���â���� �̵� </a>");
			out.print("</html>");
			out.print("</body>");
		}
	}

}
