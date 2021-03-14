package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	Connection con = null;
	Statement ps = null;
	ResultSet rs = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List listMembers() {
		
		List<MemberVO> list = new ArrayList<>();
		try {
//			connDB();
			
			String query = "select * from t_member";
			System.out.println(query);
			rs = ps.executeQuery(query);
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
	
//	private void connDB() {
//		String user = "mytest";
//		String pwd = "9654";
//		String url= "jdbc:oracle:thin:@localhost:1521/xepdb1";
//		try {
//			Class.forName(driver);
//			System.out.println("Oracle ����̹� �ε� ����");
//			con = DriverManager.getConnection(url,user,pwd);
//			System.out.println("Connection ���� ����");
//			ps = con.createStatement();
//			System.out.println("Statement ���� ����");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
