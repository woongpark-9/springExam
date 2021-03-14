package board.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.CommandProcess;

// URL패턴을 *.do로 그리고 초기 파라미터 지정
@WebServlet(
		urlPatterns = {"*.do"},
		initParams = {
				@WebInitParam(name="propertyConfig", value="Command.properties")
		})
public class ControllerAction extends HttpServlet {
	
	// 명령어와 액션을 스트링 값으로저장할 Map
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	//클라이언트의 최초요청이라면 init 호출
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// 초기 파라미터 읽어오기
		String props = config.getInitParameter("propertyConfig");
		// 명령어와 처리클래스의 정보를 매핑할 Properties 객체 생성
		// pr은 지금 비어있음 파일inputStream도 객체생성
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			// Command.properties 내용을 읽어오기
			f = new FileInputStream(new File(path, props));
			// Properties 객체에 저장하기
			pr.load(f);
		}catch (Exception e) {
			throw new ServletException(e);
		}finally {
			if(f != null) try {f.close();} catch (Exception e2) {}
		}
		
		// 반복자 객체 생성
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			// 키를 하나씩 꺼내서 getProperty로 해당키의 value를 꺼내온다
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try {
				// 해당 문자열을 클래스로 만든다
				Class commandClass = Class.forName(className);
				// 해당 클래스의 객체 생성
				Object commandInstance = commandClass.newInstance();
				// 명령어와 액션이 문자열로 담긴 properties파일을 읽어서 Map에 저장
				commandMap.put(command, commandInstance);
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
	// doGet으로 오든 doPost로 오든 requestPro에서 처리
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	// 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		// 무슨 액션이 올지모르니 부모액션으로 받아줌
		CommandAction com = null;
		try {
			
			String command = request.getRequestURI();
			System.out.println("uri : "+command);
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			System.out.println("command : "+command);
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
