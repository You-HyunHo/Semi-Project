<%@ page import="ota.model.dto.UserDTO"%>
<%@ page import="ota.model.dto.Category_auctionDTO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="ota.model.dto.AuctionDTO" %>
<%@ page import="ota.model.dto.BiddingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/views/auction/css/auctionDetail.css" />
<title>OTA 경매 물품 상세 보기</title>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<%
		String searchField = "";
		String searchWord = "";
		
// 		UserDTO userDTO = new UserDTO();
// 		AuctionDTO auctionDTO = new AuctionDTO();
// 		Category_auctionDTO categoryDTO = new Category_auctionDTO();

		Map<String, Object> detailServiceMap = (Map)request.getAttribute("detailServiceMap");
		AuctionDTO auctionDTO = (AuctionDTO)detailServiceMap.get("auctionDTO");
		List<BiddingDTO> biddingDTOList = (List<BiddingDTO>)detailServiceMap.get("biddingDTOList");
	%>
	
	<section id="detail_container">
		<div class="detail">
			<div class="detail-information">
				<div class="image">
					<img alt="사진티비" src="auction/img?auction_id=<%=auctionDTO.getAuction_id()%>"/>
				</div>
				<div class="information">
					<h2><%=auctionDTO.getAuction_title()%></h2>
					<table border="0">
						<tbody>
							<tr>
								<th>위탁자</th>
								<td><%=auctionDTO.getUser_user_id()%></td>
							</tr>
							<tr>
								<th>위탁일</th>
								<td><%=auctionDTO.getAuction_postdate()%></td>
							</tr>
							<tr>
								<th>경매 시작</th>
								<td><%=auctionDTO.getAuction_startingdate()%></td>
							</tr>
							<tr>
								<th>경매 종료</th>
								<td><%=auctionDTO.getAuction_enddate()%></td>
							</tr>
							<tr>
								<th>시작가</th>
								<td><%=auctionDTO.getAuction_startingbid()%></td>
							</tr>
							<tr>
								<th>카테고리</th>
<%-- 								<td><%=categoryDTO.getCategory_auction_name()%> --%>
							</tr>
<%

%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="detail-content">
<%-- 				<p><%=auctionDTO.getAcution_content()%></p> --%>
			</div>
			<div class="bidding-list">
				
			</div>
			<div class="bidding">
				<input>
				<button></button>
			</div>
		</div>
	
	</section>
	
	
<div style = "margin-top: 10px">
	선택한 경매 물품 '${name}'의 상세 페이지입니다.
</div>
<form action="">
	<!-- 입찰금 입력란 -->
	<input id="bid" type="text">
	<input onclick="sendBid()" value="입찰" type="button">
	<hr>
	<textarea rows="10" cols="50" id="messageTextArea"></textarea>
	<input onclick="disconnect()" value="WebSocket 서버 연결 종료" type="button">
</form>

<script type="text/javascript">
	window.onload=function(){
		webSocket = new WebSocket("ws://localhost:8080//wsAuctionDetail")

		messageTextArea = document.getElementById("messageTextArea");
		
//	 	WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(event){
//			클라이언트에 보이는 창
			console.log("웹소켓 연결됨");
//	   		클라이언트가 서버로 보내는 메시지
// 			webSocket.send("클라보냄");
			
			messageTextArea.value += "서버와 연결되었습니다...\n";
		};
			
//	 	WebSocket 서버와 통신 중 에러가 발생하면 호출되는 함수
		webSocket.onerror = function(event){
			console.log(event);
			messageTextArea.value += "에러가 발생되었습니다...\n";
		};
			
//	 	WebSocket 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(event){
			console.log(event);
			messageTextArea.value += "서버와 연결이 끊어졌습니다...\n";
		};
			
//	 	WebSocket 서버로부터 메세지가 오면 호출되는 함수
		webSocket.onmessage = function(event){
			console.log(event);
			messageTextArea.value += "[누군가] " + event.data + "\n";
		};
	};
//	입찰 버튼을 누르면 호출되는 함수
	function sendBid() {
		const message = document.getElementById("bid");
		messageTextArea.value += "[나] " + message.value + "\n";
//		webSocket 서버에 메시지를 송신한다
		webSocket.send(message.value);
		message.value = "";
	};
	
//	서버 연결 종료 버튼을 누르면 호출되는 함수
	function disconnect() {
		webSocket.close();
	};
		

</script>
<%@include file="../common/footer.jsp" %>
</body>
</html>