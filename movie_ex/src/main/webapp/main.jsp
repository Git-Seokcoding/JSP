<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<link href="${pageContext.request.contextPath }/css/default.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.7.1.js"></script>
<script>
function pop(){
	window.open("inc/popup.jsp", "pop", "width=400,height=500,history=no,resizable=no,status=no,scrollbars=yes,menubar=no")
}

	$('#exit').click(function(){
		alert("예고편 종료!")
		$('.iframe-container').remove();
	});
	
</script>
</head>
<body onLoad="javascript:pop()">
	<header>
		<jsp:include page="inc/top.jsp"></jsp:include>
	</header>
	<article>
		<nav>
			<a href="">예매</a>
			<a href="">영화</a>
			<a href="">극장정보</a>
			<a href="">스토어</a>
			<a href="">이벤트</a>
			<a href="">고객센터</a>
		</nav>
		<hr>
		<button id="exit">예고편 종료</button>
		<div class="iframe-container">
			<iframe src="https://www.youtube.com/embed/xUDhdCsLkjU?autoplay=1&mute=1&autohide=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture;" allowfullscreen></iframe>
		</div>
		<div id="Sort">
			<a href="">무비차트</a>
			<a href="">상영예정작</a>
			<a href="">관람객순</a>
		</div>
		<div id="boxoffice">
			<img src="img/서울의봄.jpeg">
			<img src="img/비투비게임.jpeg">
			<img src="img/싱글 인 서울.jpeg">
			<img src="img/프레디의 피자가게.jpeg">
			<img src="img/명탐정 코난.jpeg">
		</div>
		
		<div id="like">
			<input type="button" value=&#128077;좋아요>
			<input type="button" value=&#128077;좋아요>
			<input type="button" value=&#128077;좋아요>
			<input type="button" value=&#128077;좋아요>
			<input type="button" value=&#128077;좋아요>
		</div>
		
		
		
	</article>
	<footer>
		<jsp:include page="inc/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>



