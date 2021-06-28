package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	public int login(String userID, String userPassword) {
		String SQL = "select userPassword from USER where userID = ?";
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
			rset = pstmt.executeQuery();
			if (rset.next()) {
				if(rset.getString(1).equals(userPassword)) {
					return 1;//login success
				} else {
					return 0;//login failed (wrong password)
				}
			}
			return -1; //ID doesn't exist			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return -2;//database error
	}
	
	public int join(UserDTO user) {
		String SQL = "insert into USER values (?, ?, ?, ?, false)";
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
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();//1 success		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return -1;//join failed
	}
	
	public String getUserEmail(String userID) {
		String SQL = "select userEmail from USER where userID = ?";
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
			rset = pstmt.executeQuery();	
			if (rset.next()) {
				return rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return null;//database error		
	}

	public boolean getUserEmailChecked(String userID) {
		String SQL = "select userEmailChecked from USER where userID = ?";
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
			rset = pstmt.executeQuery();	
			if (rset.next()) {
				return rset.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return false;//database error
	}
	
	public boolean setUserEmailChecked(String userID) {
		String SQL = "update USER set userEmailChecked = true where userID = ?";
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
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return false;//database error
	}		
	
}
