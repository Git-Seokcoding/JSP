package jsp11_jdbc_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jsp10_jdbc_dbcp.JdbcUtil;

public class StudentDAO {

	public int insert(StudentDTO student) {
		System.out.println("StudentDAO - insert()");
		
		// 최종적으로 리턴할 int타입 변수
		int insertCount = 0;
		
		// 데이터베이스 활용에 사용될 변수 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//JdbcUtil - getConnection() 메서드 호출하여 1~2단계 실행
		con = JdbcUtil.getConnection();
		
		try {
			// 3단계. SQL 구문 작성 및 전달
			// => jsp09_student 테이블에 번호, 이름 추가(INSERT)
			String sql = "INSERT INTO jsp09_student VALUES (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getIdx());
			pstmt.setString(2, student.getName());
			System.out.println(pstmt);
			
			// 4단계. SQL 구문 실행 및 결과 처리
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// DB 연결(2단계) 까지의 처리는 JdbcUtil 클래스의 getConnection() 메서드에서
			// try/catch 를 통해 예외 처리를 했기 때문에 SQL 구문 오류만 남음
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			// => JdbcUtil 클래스의 static 메서드 close() 메서드를 호출하여
			//    반환할 자원(객체)을 파라미터로 전달
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		// 모든 작업을 완료하고 작업 결과가 저장된 변수값(insertCount) 리턴
		return insertCount;
	}// insert 메소드 끝
	
	//학생 목록 조회 작업을 수행하는 selectStudentList() 메서드 정의
	//파라미터 x 리턴 void
	public List<StudentDTO> selectStudentList() {
		// 학생 1명의 정보~
		
		List<StudentDTO> studentList = null;
		// 데이터베이스 활용에 사용될 변수 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// JdbcUtil 클래의 getConnection() 메서드를 호출하여
		// 커넥션풀을 통해 관리되는 Connection 객체 리턴받기
		// => static 메서드이므로 클래스명만으로 접근 가능
		// => 1단계 & 2단계 작업에 해당
		con = JdbcUtil.getConnection();
		
		try {
			// 3단계. SQL 구문 작성 및 전달
			// => jsp09_student 테이블의 모든 레코드 조회
			String sql = "SELECT * FROM jsp09_student";
			pstmt = con.prepareStatement(sql);
			
			// 4단계. SQL 구문 실행 및 결과 처리
			rs = pstmt.executeQuery();
			
			// 레코드가 존재할 동안 반복하면서 데이터 출력
			// =>단, 복수개의 레코드(StudentDTO 객체 여러개)를 하나의 묶음으로 관리하기 위해
			// 배열을 사용할 수도 있으나 결과가 몇개인지 몰라서 사용할 수가 없다!
			// 자바의 배열은 크기 변경 불가능!
			// 따라서, 배열 대신 java.util.List 타입 객체 (ArrayList 클래스 활용)를 사용하여
			// 복수개의 StudentDTO 객체를 지정할 수 있다!
			// => 반복문 내에서 StudentDTO 객체를 저장할 List(ArrayList)객체를 반복문 밖에서 미리 생성 필요
			studentList = new ArrayList<StudentDTO>();
			
			
			
			while(rs.next()) {
				// 각 레코드의 컬럼 접근을 위해 ResultSet 객체의 getXXX() 메서드 호출
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
//						System.out.println("번호 : " + idx + ", 이름 : " + name);
				
				// 하나의 레코드 (=한 명의 학생 정보) 의 모든 정보를
				// 각각의 변수x 객체(묶음_) 관리 studentDTO 클래스 활용
				StudentDTO student = new StudentDTO();
				student.setIdx(idx);
				student.setName(name);
				//1개 레코드가 저장된 StudentDTO 객체를 List 객체에 추가
				//-> List객체의 add()메서드 활용
				// 단, List객체는 반복문 밖에서 생성되어 있어야 한다.
				studentList.add(student);
				
				
			}
 			
		} catch (SQLException e) {
			// DB 연결(2단계) 까지의 처리는 JdbcUtil 클래스의 getConnection() 메서드에서
			// try/catch 를 통해 예외 처리를 했기 때문에 SQL 구문 오류만 남음
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// DB 자원 반환
			// => JdbcUtil 클래스의 static 메서드 close() 메서드를 호출하여
			//    반환할 자원(객체)을 파라미터로 전달
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		//복수개의 레코드가 저장된 List 객체(studentList)
		return studentList;
				
	}
	
	
	
}