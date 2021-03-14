package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TempMemberDAO {
//	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
//	private final String USER ="mytest";
//	private final String PASS ="9654";
//	private ConnectionPool pool;
	
	public TempMemberDAO() {
//		try {
//			pool = ConnectionPool.getInstance();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
			con = ds.getConnection();
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public Vector<TempMemberVO> getMemberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Vector<TempMemberVO> list = new Vector<TempMemberVO>();
		
		try {
			con = this.getConnection();
			String sql = "select * from TEMPMEMBER";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				TempMemberVO vo = new TempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setEmail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(st!=null)try {st.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {}
		}
		return list;
		
	}
}
