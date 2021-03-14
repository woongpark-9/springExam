package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;


public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		// 검색옵션 검색 내용
		String search = request.getParameter("search");
		String searchvalue = request.getParameter("searchvalue");
		
		System.out.println(search);
		System.out.println(searchvalue);
		String pageNum = request.getParameter("pageNum"); // 
		// pageNum이 null이면 기본값 1 설정
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5;  // 몇개의 글이 보여지게 할건지
		int currentPage = Integer.parseInt(pageNum); // 현재페이지
		int startRow = (currentPage - 1) * pageSize + 1; // 시작 글 번호
		int endRow = currentPage * pageSize; // 끝나는 글 번호
		int count = 0; // 전체글의수
		int number = 0; // 글 번호
		BoardDAO dbPro = BoardDAO.getInstance();
		count = dbPro.getArticleCount();
		List<BoardVO> articleList = null;
		if(search != null && searchvalue != null && count > 0) {
			 articleList = dbPro.getArticles(startRow, endRow,search,searchvalue);
		}else if(searchvalue == null && count >0) {
			articleList = dbPro.getArticles(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
	
	
		
		number = count - (currentPage - 1) * pageSize;
		
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("articleList", articleList);
		return "/board/list.jsp";
	}

}
