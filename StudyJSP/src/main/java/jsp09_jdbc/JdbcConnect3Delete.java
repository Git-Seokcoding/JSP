package jsp09_jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JdbcConnect3_DELETE") // http://localhost:8080/StudyJSP/JdbcConnect3_DELETE
public class JdbcConnect3Delete extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JdbcConnect3Delete");
		
		// DB 자원을 관리하는 Connection, PreparedStatement 등의 타입 변수 선언
		// => finally 블럭에서 접근하기 위함
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 0단계. JDBC 연결에 필요한 문자열을 각각의 변수에 저장
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/study_jsp5";
			String user = "root";
			String password = "1234";
			
			// 1단계. JDBC 드라이버 로드
			Class.forName(driver);
			System.out.println("드라이버 로드 성공!");
			
			// 2단계. DB 연결
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결 성공!");
			
			// 3단계. SQL 구문 작성 및 전달
			// 번호가 4, 이름이 '김태희' 인 레코드 삭제
			int idx = 4;
			String name = "김태희";
			
			String sql = "DELETE FROM jsp09_student WHERE idx = ? AND name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx); // 첫번째 파라미터값을 idx 변수값으로 교체
			pstmt.setString(2, name); // 두번째 파라미터값을 name 변수값으로 교체
			System.out.println(pstmt);
			
			// 4단계. SQL 구문 실행 및 결과 처리
			int deleteCount = pstmt.executeUpdate();
			System.out.println("삭제 성공! - " + deleteCount);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패! 또는 SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반환(Connection, PreparedStatement)
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}












