package sec04.ex01;


import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/**
 * Servlet implementation class LoginImpl
 */

public class LoginImpl implements HttpSessionBindingListener {
	String user_id;
	String user_pw;
	static int total_user = 0;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginImpl() {
        super();
        // TODO Auto-generated constructor stub
    }
    public LoginImpl(String user_id , String user_pw) {
    	this.user_id = user_id;
    	this.user_pw = user_pw;
    	
    }
    public void valueBound(HttpSessionBindingEvent arg0) {
    	System.out.println("사용자 접속");
    	++total_user;
    	
    }
    public void valueUnbound(HttpSessionBindingEvent arg0) {
    	System.out.println("사용자 접속 해제");
    	total_user--;
    }

	

}
