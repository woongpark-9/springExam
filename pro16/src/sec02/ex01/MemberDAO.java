package sec02.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DataSource ds;
	
	public boolean overlappedID(String id ) {
		boolean result = false;
		try {
			con = ds.getConnection();
			String query = "select decode(count(*),1,'true','false') as result from t_member where id=?";
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			rs =ps.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	return result;
	}
}
