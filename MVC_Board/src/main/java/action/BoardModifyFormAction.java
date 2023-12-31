package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardModifyFormAction");
		
		ActionForward forward = null;
		
		// 세션 아이디 저장(단, 세션 아이디가 없을 경우 자바스크립트 오류 출력 및 이전페이지로 이동)
		// 세션 아이디 존재하지 않을 경우 처리
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		
		// 자바스크립트 사용하여 "잘못된 접근입니다!" 출력 후 메인페이지로 이동
		if(id == null) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다!');");
				out.println("location.href = './';");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// 세션 아이디 없음 처리 정보 설정 후 메서드 수행 종료 후 복귀하려면 null 값 리턴
			return null;
		}
		
		// 글번호 파라미터 가져오기
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		// BoardDetailService - getBoard() 메서드 호출하여 글 상세정보 조회 요청
		// => 파라미터 : 글번호   리턴타입 : BoardBean(board)
		// => 주의! BoardModifyFormService 클래스를 새로 정의하는 것이 아니라
		//    기존의 글 상세정보 조회 기능을 재사용하기 위해 BoardDetailService 클래스 재사용
		// => 추가사항) 글 수정, 답글 작성과 구분하여 조회수 증가도 함께 수행하기 위해
		//    boolean 타입 값을 신호로 전달(true : 조회수 증가, false : 조회수 미증가)
		BoardDetailService service = new BoardDetailService();
		BoardBean board = service.getBoard(board_num, false); // 조회수 증가 X
//		System.out.println(board);
		
		// 세션 아이디와 조회 결과 중 작성자가 동일한지 판별
		// => 다를 경우 자바스크립트 사용하여 "수정 권한이 없습니다!" 출력 후 이전페이지로 돌아가기
		if(!id.equals(board.getBoard_name())) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 권한이 없습니다!');");
				out.println("history.back();");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		// 1개 게시물 조회 결과(BoardBean 객체)를 request 객체에 저장
		request.setAttribute("board", board);
		
		// ActionForward 객체를 사용하여 "board/board_modify_form.jsp" 포워딩 정보 설정
		forward = new ActionForward();
		forward.setPath("board/board_modify_form.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}










