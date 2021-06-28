package evaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluationDAO {
	
	public int write(EvaluationDTO evaluationDTO) {
		String SQL = "insert into EVALUATION values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
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
			pstmt.setString(1, evaluationDTO.getUserID().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, evaluationDTO.getLectureName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, evaluationDTO.getProfessorName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setInt(4, evaluationDTO.getLectureYear());
			pstmt.setString(5, evaluationDTO.getSemesterDivide().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, evaluationDTO.getLectureDivide().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(7, evaluationDTO.getEvaluationTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(8, evaluationDTO.getEvaluationContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(9, evaluationDTO.getTotalScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(10, evaluationDTO.getCreditScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(11, evaluationDTO.getComfortableScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(12, evaluationDTO.getLectureScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			return pstmt.executeUpdate();//성공적으로 등록되었다면 1이라는 값	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return -1;//database error
	}
	
	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {
		if (lectureDivide.equals("전체")) {
			lectureDivide = "";
		}
		ArrayList<EvaluationDTO> evaluationList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		String SQL = "";
	
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			String DBURL = "jdbc:mysql://localhost:3306/LectureEvaluation";
			String DBID = "root";
			String DBPASSWORD = "gusehdqls92410!@";
			conn = DriverManager.getConnection(DBURL, DBID, DBPASSWORD);
			if (searchType.equals("최신순")) {
				SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE ? ORDER BY evaluationID DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;
			} else if (searchType.equals("추천순")) {
				SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE ? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;
			}

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + lectureDivide + "%");
			pstmt.setString(2,  "%" + search + "%");
			rset = pstmt.executeQuery();
			evaluationList = new ArrayList<EvaluationDTO>();
			while (rset.next()) {
				EvaluationDTO evaluation = new EvaluationDTO(
						rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getInt(5),
						rset.getString(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9),
						rset.getString(10),
						rset.getString(11),
						rset.getString(12),
						rset.getString(13),
						rset.getInt(14)
				
				);
				evaluationList.add(evaluation);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return evaluationList;
	}
	
	public int like(String evaluationID) {
		String SQL = "update EVALUATION set likeCount = likeCount + 1 where evaluationID = ?";
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
			pstmt.setInt(1, Integer.parseInt(evaluationID));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return -1;//error
	}
	
	public int delete(String evaluationID) {
		String SQL = "delete from EVALUATION where evaluationID = ?";
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
			pstmt.setInt(1, Integer.parseInt(evaluationID));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rset != null) rset.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace();}
			try {if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace();}
		}
		return -1;//error
	}
	
	public String getUserID(String evaluationID) {
		String SQL = "select userID from EVALUATION where evaluationID = ?";
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
			pstmt.setInt(1, Integer.parseInt(evaluationID));
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
		return null;//doesn't exist
	}	
	

}
