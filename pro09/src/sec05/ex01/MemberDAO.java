package sec05.ex01;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private DataSource dataFactory;
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String user = "mytest";
	private static final String pwd = "9654";
	private static final String url= "jdbc:oracle:thin:@localhost:1521/xepdb1";
	
	public MemberDAO() {
		try
		{
		Context ctx = new InitialContext();
//		Context envContext = (Context) ctx.lookup("java:/comp/env");
//		dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void addMember(MemberVO memberVO) {
		try {
			Connection con = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			String query = "insert into t_member";
			query+= " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			System.out.println("query : "+query);
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, name);
			ps.setString(4, email);
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void delMember(String id) {
		String query = "delete from t_member where id=?";
		try {
			con = dataFactory.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			
		}
	}
	
	public List listMembers() {
		
		List<MemberVO> list = new ArrayList<>();
		try {
		//	connDB();
		
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println(query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setDate(joinDate);
				list.add(vo);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public boolean isExisted(MemberVO vo) {
		boolean result = false;
		String id = vo.getId();
		String pwd = vo.getPwd();
		ResultSet rs = null;
		try {
			con = dataFactory.getConnection();
			String sql = "select decode(count(*),1,'true','false')as result from t_member where id=? and pwd=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result="+result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

//	private void connDB() {
//		
//		try {
//			Class.forName(driver);
//			System.out.println("Oracle 드라이버 로딩 성공");
//			
//			con = DriverManager.getConnection(url,user,pwd);
//			System.out.println("Connection 생성 성공");
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
