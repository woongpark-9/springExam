package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어떤 액션이 들어올지 모르니 다형성을 이용해 구현
public interface CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
