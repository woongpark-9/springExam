package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/viewMember")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List memberList = (List) request.getAttribute("memberList");
		out.print("<html><body>");
		out.print("<table border=1> <tr align='center' bgcolor='lightgreen'>");
		out.print("<td>���̵�</td><td>��й�ȣ</td><td>�̸�</td><td>�̸���</td>"
				+ "<td>������</td><td>����</td><tr>");
		for(int i =0; i<memberList.size(); i++) {
			MemberVO memberVO = (MemberVO) memberList.get(i);
			out.print("<tr><td>"+memberVO.getId() +"</td><td>" +memberVO.getPwd()+"</td><td>"+memberVO.getName()+"</td><td>"
					+ memberVO.getEmail()+"</td><td>"+memberVO.getDate()+"</td><td>"
					+"<a href='/pro7/member3?command=delMember&id="+memberVO.getId()+"'>���� </a></td></tr>");
		}
		out.print("</table></body></html>");
		out.print("<a href='/pro07/memberForm.html'>�� ȸ�� ����ϱ�</a>");
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
