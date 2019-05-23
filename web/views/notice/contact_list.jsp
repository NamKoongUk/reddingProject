<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "java.util.* , com.kh.redding.board.model.vo.*"  %>
<% 
ArrayList<HashMap<String, Object>> QnAList = (ArrayList<HashMap<String, Object>>)request.getAttribute("QnAList");
BoardPageInfo pi = (BoardPageInfo) request.getAttribute("pi");
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();

%>
<!DOCTYPE html>
<html>
<head>
<title>REDDING♥</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR"
	rel="stylesheet"><!-- 글꼴 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/member/m_header.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/member/m_nav.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/notice/contact_list.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/footer.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<style>

.container {
	width:100%;
	color:salmon;
	font-family:'Noto Sans KR', sans-serif;
}

.row.content {
	height: 600px;
}

.sidenav {
	height: 100%;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
	
	.noticetable{
		width : 400px;
	}
}
</style>
</head>
<body>

	<!-- 멤버 헤더 (미니메뉴, 로고) -->
	<div class="headerArea">
		<%-- <jsp:include page="/views/member/m_header.jsp"></jsp:include> --%>
		<%@ include file="/views/member/m_header.jsp"%>
	</div><br>

	<!-- 멤버 나브 -->
	<div>
		<jsp:include page="/views/member/m_nav.jsp"></jsp:include>
	</div>

	<!-- 섹션 -->
	<div class="container-fluid text-center">
		<div class="row content">
			
			<!-- 왼쪽 빈공간 -->
			<div class="col-sm-2 sidenav"></div>

			<div class="col-sm-8 text-left">
			<%-- ---------------------------------------------- 여기만 작성하세요 ---------------------------------------------- --%>

			<div class="container">
				<h2>1대 1 문의</h2>
				<hr>
					<div class="wrap">
						<div class="conMenu">
							<div class="writerbtn">
								<i class="material-icons md-36">border_color</i>
								<div class="labeldiv">
									<label>작성하기</label>
								</div>
							</div>
							<div class="listbtn">
								<i class="material-icons md-36" id="writeIcon">insert_comment</i>
								<div class="labeldiv">
									<label id="wlabel">문의내역</label>
								</div>
							</div>
						</div>
						<div class="contactwrite">
							<h3>문의내역</h3>
							<br>
							<table class="contacttable">
								<thead>
									<tr>
										<th>NO</th>
										<th>제목</th>
										<th>작성일</th>
										<th>진행상태</th>
									</tr>
								</thead>
								<tbody>
								<% if (QnAList == null || QnAList.size() == 0){ %>
									<tr id="contactTitle" onclick="NoticeClick(this);">
										<td></td>
										<td colspan = "2">등록된 게시글이 없습니다</td>
										<td></td>
									</tr>
								<% }else { %>
									<% for(int i = 0 ; i < QnAList.size() ; i++){ 
										HashMap hmap = QnAList.get(i);
										Board qna = (Board)hmap.get("QnA");
										int no = (int)hmap.get("num");
										Reply re = (Reply)hmap.get("reply");
										int recode = re.getReply_code();
									%>
									<tr id="contactTitle" onclick="NoticeClick(this);">
										<td><%=no %></td>
										<td><%=qna.getBtitle() %></td>
										<td><%=qna.getBdate() %></td>
										<% if (recode == 0){ %>
										<td><span style = "color:red">진행중</span></td>
										<%}else { %>
										<td><span style = "color:blue">진행완료</span></td>
										<%} %>
									</tr>
									<tr id = "noticecontent" style = "display:none;">
										<td colspan = "4">
											<span id = "Q" style= "color: red;">Q</span><br>
											<span id = "noticeContent">
												<%=qna.getBcontent() %>
											</span>
											<hr>
											<span id = "A" style= "color: blue;">A</span><br>
											<% if (recode != 0){ %>
												<span><%=re.getReply_content() %></span>
											<%}else{ %>
												<span>아직 답변을 하지 않았습니다.</span>
											<%} %>
										</td>
									</tr>
									<%} %>
								<% }%>
								</tbody>
							</table>
							<br>
							<div class = "notice_footer">
						<!-- 페이지 버튼 처리 -->
						<div class="pagingArea" align="center">
						<button onclick="location.href='<%= request.getContextPath() %>/selectnotice.no?currentPage=1'"><<</button>
						<% if(currentPage <= 1) { %>
						<button disabled><</button>
						<% } else { %>
						<button onclick="location.href='<%= request.getContextPath() %>/selectnotice.no?currentPage=<%= currentPage - 1 %>'"><</button>
						<% } %>
						
						<% for(int p = startPage; p <= endPage; p++) { 
								if(p == currentPage) { %>
									<button disabled><%= p %></button>
						<% 		}else {%>
									<button onclick="location.href='<%= request.getContextPath() %>/selectnotice.no?currentPage=<%= p %>'"><%= p %></button>
						<%		} %>
							
						<% } %>
						
						<% if(currentPage >= maxPage) { %>
						<button disabled>></button>
						<% } else { %>
						<button onclick="location.href='<%= request.getContextPath() %>/selectnotice.no?currentPage=<%= currentPage + 1 %>'">></button>
						<% } %>
						<button onclick="location.href='<%= request.getContextPath() %>/selectnotice.no?currentPage=<%= maxPage %>'">>></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						
				</div>
			</div>				
		</div>


		</div>
			
			<!-- 오른쪽 빈공간 -->
			<div class="col-sm-2 sidenav"></div>
			
		</div>

	</div>
	<br>
	
	<!-- 커먼 풋터 -->
	<div class="footerArea">
		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
	
	<script>
		function NoticeClick(obj){
			var tr = $(obj);
			var sub = tr.next();
		
			if(sub.is(":visible")){
				sub.slideUp();
			}else{
				sub.slideDown();
			}
		
		}
	
		$(function(){
			$(".listbtn").click(function(){
				location.href = "<%=request.getContextPath()%>/selectQnA.no?mno="+ mno;
			});
			
			$(".writerbtn").click(function(){
				location.href = "<%=request.getContextPath()%>/views/notice/contact_main.jsp";
			});
		})
		
	</script>

</body>
</html>
