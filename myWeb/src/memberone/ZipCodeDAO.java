package memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ZipCodeDAO {
	private static ZipCodeDAO instance = null;
	
	public static ZipCodeDAO getInstance() {
		if(instance == null) {
			synchronized(ZipCodeDAO.class) {
				instance = new ZipCodeDAO();
			}
		}
		return instance;
	}
	private Connection getConnection() {
		Connection con = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		Vector<ZipCodeVO> list = new Vector<ZipCodeVO>();
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like '"+dong+"%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ZipCodeVO vo = new ZipCodeVO();
				vo.setZipcode(rs.getString("zipcode"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setDong(rs.getString("dong"));
				vo.setRi(rs.getString("ri"));
				vo.setBunji(rs.getString("bunji"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(ps!=null)try {ps.close();}catch(SQLException e) {}
			if(con!=null)try {con.close();}catch(SQLException e) {} 
		}
		return list;
	}
}
