package likey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeyDAO {
	
	public int like(String userID, String evaluationID, String userIP) {
		String SQL = "insert into LIKEY values (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String DBURL = "jdbc:mysql://localhost:3306/LectureEvaluation";
			String DBID = "root";
			String DBPASSWORD = "gusehdqls92410!@";
			conn = DriverManager.getConnection(DBURL, DBID, DBPASSWORD);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, evaluationID);
			pstmt.setString(3, userIP);
			return pstmt.executeUpdate();//1 success		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return -1;//duplication error

	}
	
}
