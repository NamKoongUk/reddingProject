<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='../../packages/core/main.css' rel='stylesheet' />
<link href='../../packages/daygrid/main.css' rel='stylesheet' />
<script src='../../packages/core/main.js'></script>
<script src='../../packages/interaction/main.js'></script>
<script src='../../packages/daygrid/main.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/semantic/semantic.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/calendar.css">



<style>




</style>
</head>
<body>
	<br><br>
	<table style="width:70%; margin-left:auto; margin-right:auto">
		<tr>
  			<td width="40%" height="470px">
  				<div id='calendar'></div>
  			</td>
  			
  			<td class="visible-md visible-lg visible-sm" width="30%" style="padding:5%">
				<h2 align="center">예약 수정</h2>
				<br>
				<table style="width:100%">
					<tr>
						<td>
						<label>상품명</label><input class="form-control" id="productName" type="text" style="width:100%; height:30px" readonly>
							<br>
						</td>
					</tr>
					<tr>
						<td>
							<label>예약날짜</label>
							<br>
							<input class="form-control" id="productDate" type="datetime-local" style="width:100%; height:30px; float:left; margin-right :20px" readonly>
							<br><br><br>
						</td>
					</tr>	
					<tr>
						<td>
							<label>예약자</label>
							<br>
							<input class="form-control" id="productDate" type="text" style="width:100%; height:30px; float:left; margin-right :20px" readonly>
							<br><br><br>
						</td>
					</tr>
				
						
					<tr>
						<td>
						<label>예약승인</label>
						<br>
							<div class="ui toggle checkbox">
  							<input type="checkbox" name="public" id="changeStatus">
  							<label></label>
						</div>
						<br><br><br>
						</td>
					</tr>
				</table>
				<div style="text-align:center">
					<button class="ui button" id="changeRservation">예약수정</button>
					<button class="ui button">예약취소</button>
				</div>
				
			</td>
			</tr>
		</table>
	<script>
	
	//ajax 처리용 변수
	
	var changeDate;
	var dateForm;
	
 	document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
	
	
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid' ],
      defaultDate: '2019-05-14',
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      events: [
        {
          title: 'Meeting',
          start: '2019-05-14T10:00:00',
          end: '2019-05-14T12:00:00',
          color:"salmon"
        },
        {
            title: 'Mee',
            start: '2019-10-14T10:00:00',
            end: '2019-10-14T12:00:00',
            color:"lightgray"
          }
          
      ],
      
      dateClick:function(event) {
    	  /*  changeDate = event.dateStr;
    	  
    	  var sysdate = new Date();
    	  var checkdate = new Date(changeDate);
    	  
    	  changeDateForm = changeDate + "T01:00";
    	  console.log(changeDateForm);
    	  if(sysdate > checkdate){
    		  window.alert("현재 일자보다 더 큰 일자를 선택하세요");
    	  }
    	  
    	  if($("#productName").val() == ""){
    		  window.alert("예약을 수정할 이벤트를 먼저 클릭하세요");
    	  }else{
    		  $("#reservationTime").val(changeDateForm);
    	  }  */
    	  
    	  
    	  
      },
    eventClick:function(info){
    	console.log(info.event);
    	var beforeDate = info.event.start;
    	
    	beforeTime = beforeDate.getHours() + ":00"; //시간 계산
    	
    	if(info.event.backgroundColor === "salmon"){
    		$("#changeStatus").attr("checked",true);
    	}else{
    		$("#changeStatus").attr("checked",false);
    	}
    	
    	//날짜 계산
    	if(beforeDate.getMonth()<9){
    		dateForm = beforeDate.getFullYear()+ '-0' + (beforeDate.getMonth()+1) + '-' + beforeDate.getDate() + "T" + beforeTime;
    	}else{
    		dateForm = beforeDate.getFullYear()+ '-' + (beforeDate.getMonth()+1) + '-' + beforeDate.getDate() + "T" + beforeTime;
    	}
 
    	$("#productName").val(info.event.title);
    	$("#productDate").val(dateForm);

    },
      
    
    });

    calendar.render();
    
  });
  
  $(function(){
	 
	  $.ajax({
		url: "<%=request.getContextPath() %>/calendar.po",
		type:"get",
		success:function(data){
			console.log(data); 
		},
		error:function(data){
			console.log("error : " + data);
		}
		 
	 });
	  
	$("#changeRservation").click(function(){
		$.ajax({
			
		});
	});
	  
  });

</script>
</body>
</html>