<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><button class="navBtn" onclick="location.href='<%=request.getContextPath() %>/views/member/m_studioList.jsp'">&nbsp;&nbsp;&nbsp;STUDIO&nbsp;&nbsp;&nbsp;</button></li>
					<li><button class="navBtn" onclick="location.href='<%=request.getContextPath() %>/views/member/m_dressList.jsp'">&nbsp;&nbsp;&nbsp;DRESS&nbsp;&nbsp;&nbsp;</button></li>
					<li><button class="navBtn" onclick="location.href='<%=request.getContextPath() %>/views/member/m_mhList.jsp'">&nbsp;&nbsp;&nbsp;MAKEUP&HAIR&nbsp;&nbsp;&nbsp;</button></li>
					<li><button class="navBtn" onclick="location.href='<%=request.getContextPath() %>/selectBoardList.bo'">&nbsp;&nbsp;&nbsp;COMMUNITY&nbsp;&nbsp;&nbsp;</button></li>
					<li><button class="navBtn" style="color: gray" onclick="location.href='<%=request.getContextPath()%>/views/company/c_main.jsp'">&nbsp;&nbsp;&nbsp;COMPANY&nbsp;&nbsp;&nbsp;</button></li>
					<li><button class = "navBtn" id = "noticebtn">&nbsp;&nbsp;&nbsp;고객센터</button>
						<ul class="nav navbar-nav" id = "noticemenu">
							<li><button class = "navBtn" onclick= "location.href='<%=request.getContextPath() %>/views/notice/notice_main.jsp'" >&nbsp;&nbsp;&nbsp;공지사항&nbsp;&nbsp;&nbsp;</button></li>
							<li><button class = "navBtn" onclick= "location.href='<%=request.getContextPath() %>/views/notice/FAQ_main.jsp'">&nbsp;&nbsp;&nbsp;FAQ&nbsp;&nbsp;&nbsp;</button></li>
							<li><button class = "navBtn" onclick= "location.href='<%=request.getContextPath() %>/views/notice/contact_main.jsp'">&nbsp;&nbsp;&nbsp;1대1문의&nbsp;&nbsp;&nbsp;</button></li>
						</ul>
					</li>
				</ul>
				
			</div>
		</div>
	</nav>
	<script>
		$(function(){
			$("#noticebtn").click(function(){
				var submenu = $("#noticemenu")
				if(submenu.is(":visible")){
					submenu.slideUp();
				}else{
					submenu.slideDown();
				}
			});
		});
	
	</script>
</body>
</html>

