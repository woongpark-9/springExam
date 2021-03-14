package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import boardone.ConnUtil;


// BoardDataAccessObject 
public class BoardDAO {

	//싱글톤 적용한 dao
	private static BoardDAO instance = null;
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	

	// 전체 글 개수 가져오는 메서드
	public int getArticleCount() {
		int count = 0;
		String sql = "select count(*) from board";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) count = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	// 전체 글을 가져오는 메서드
	public List<BoardVO> getArticles(int start, int end) {
		List<BoardVO> articleList = new ArrayList<BoardVO>();
		String sql = "select * from (select rownum rnum, num, writer, email, "
				+ "subject, pass, regdate, readcount, ref, step, depth, content, ip from ("
				+ "select * from board order by ref desc, step asc)) where rnum >= ? and rnum <= ?"; 
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					BoardVO article = new BoardVO(
							rs.getInt("num"), rs.getString("writer"), 
							rs.getString("email"), rs.getString("subject"), 
							rs.getString("pass"), rs.getInt("readcount"), 
							rs.getInt("ref"), rs.getInt("step"), 
							rs.getInt("depth"), rs.getTimestamp("regdate"), 
							rs.getString("content"), rs.getString("ip"));
					articleList.add(article);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	// 내가 검색한 글을 가져와주는 메서드 (오버로딩 하였음)
	public List<BoardVO> getArticles(int start, int end,String search , String searchvalue) {
		List<BoardVO> articleList = new ArrayList<BoardVO>();
				
				String sql = "select * from(select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from (select * from board where "
						+search+" like '%"+searchvalue+"%' order by ref desc, step asc)) where rnum>=? and rnum<=?";
				// 제목 + 내용 조건으로 검색했을경우 쿼리문이 달라서 조건을 걸어준다
				// 제목이나 내용에 해당 value가 있는값을 가져옴
				if(search.equals("subjectAndContent")) {
					sql = "select * from(select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from (select * from board where " +"subject like '%"+searchvalue+"%' or content like '%"+searchvalue+"%' order by ref desc, step asc)) where rnum>=? and rnum<=?";
				}
				try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			try(ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					BoardVO article = new BoardVO(
							rs.getInt("num"), rs.getString("writer"), 
							rs.getString("email"), rs.getString("subject"), 
							rs.getString("pass"), rs.getInt("readcount"), 
							rs.getInt("ref"), rs.getInt("step"), 
							rs.getInt("depth"), rs.getTimestamp("regdate"), 
							rs.getString("content"), rs.getString("ip"));
					articleList.add(article);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	// 최근 글의 참조하는번호를 가져오는 메서드
	public int maxNum() {
		int maxNum = 1;
		String sql = "select max(ref) from board";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			if(rs.next()) maxNum = rs.getInt(1) + 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return maxNum;
	}
	
	// 그룹내에 나보다 먼저 답글이 있다면 step1 우선순위 증가해주는 메서드
	public void updateAnswer(int ref, int step) {
		String sql = "update board set step=step+1 where ref=? and step>?";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 글 작성 메서드
	public void insertArticle(BoardVO article) {
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "insert into board(num, writer, email, subject, pass, "
				+ "regdate, ref, step, depth, content, ip) "
				+ "values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			number = maxNum();
			if(num != 0) {
				updateAnswer(ref, step);
				step = step + 1;
				depth = depth + 1;
			}else {
				ref = number;
				step = 0;
				depth = 0;
			}
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 해당 글 보여주는 메서드
	public BoardVO getArticle(int num) {
		BoardVO article = null;
		String sql = "select * from board where num = ?";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					article = new BoardVO(rs.getInt("num"), rs.getString("writer"), 
							rs.getString("email"), rs.getString("subject"), 
							rs.getString("pass"), rs.getInt("readcount"), 
							rs.getInt("ref"), rs.getInt("step"), 
							rs.getInt("depth"), rs.getTimestamp("regdate"), 
							rs.getString("content"), rs.getString("ip"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	// 조회수 증가 메서드
	public void upHit(int num) {
		String sql = "update board set readcount=readcount+1 where num = ?";
		try (Connection conn = ConnUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 수정작업메서드
	public int updateArticle(BoardVO article) {
		String sql = "update board set writer=?, email=?, subject=?, content=? where num=?";
		int result = -1;
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			String dbpasswd = getPass(article.getNum());
			if(dbpasswd.equals(article.getPass())) {
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4, article.getContent());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
				result = 1;
			}else {
				result = 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public String getPass(int num) {
		String sql = "select pass from board where num=?";
		String dbPass = "";
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) dbPass = rs.getString("pass");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dbPass;
	}
	

	public int deleteArticle(int num, String pass) {
		String sql = "delete from board where num = ?";
		int result = -1;
		try(Connection conn = ConnUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			String dbpasswd = getPass(num);
			System.out.println(dbpasswd);
			if(dbpasswd.equals(pass)) {
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				result = 1;
			}else {
				result = 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
