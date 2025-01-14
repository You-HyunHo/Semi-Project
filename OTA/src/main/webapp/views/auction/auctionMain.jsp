<%@ page import="ota.model.dao.AuctionDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="ota.model.dao.Category_auctionDAO" %>
<%@ page import="ota.model.dto.Category_auctionDTO" %>
<%@ page import="ota.model.dto.AuctionDTO" %>
<%@ page import="ota.model.dto.BiddingDTO" %>
<%@ page import="ota.model.dao.BiddingDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTA 경매 메인</title>
<link rel="stylesheet" href="/views/auction/css/auctionMain.css" />
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<%
		String searchField = "";
		String searchWord = "";
// 		Category_auctionDAO category_auctionDAO = new Category_auctionDAO();
// 		List<Category_auctionDTO> category_auctionDTO = category_auctionDAO.categoryList();
// 		int categoryWidth = category_auctionDTO.size();
		
// 		AuctionDAO auctionDAO = new AuctionDAO();
// // 		List<AuctionDTO> auctionDTOList = auctionDAO.selectListAuction(new HashMap<String, Object>());
		
// 		BiddingDAO biddingDAO = new BiddingDAO();
// 		List<BiddingDTO> biddingDTOList = biddingDAO.selectListBidding(new HashMap<String, String>());
		
// 		auctionDAO의 selectAllAuction_idList로 auction_idList받아와서
//		BiddingDAO의 selectHighestBid에 넣어서 Map<auction_id, bidding_bid>을 받음
//		Map<auction_id, bidding_bid>의 엔트리 수 만큼 thumnail-box 생성
//		thumnail-box에 필요한 것: auctionDTO.img, auctionDTO.title, auctionDTO.id-bidding_bid(최고 입찰가)
//		AuctionController에 수정된 auctionDetail연결 필요

// 		List<Integer> allAuction_idList = auctionDAO.selectAllAuction_idList();
// 		Map<Integer, Integer> highestBidMap = biddingDAO.selectHighestBid(allAuction_idList);
		
		
		// 10월 14일 'Re:제로부터 시작하는 MVC2개발' 시작
		Map<String, Object> mainServiceMap = (Map)request.getAttribute("mainServiceMap");
		
// 		if (mainServiceMap == null) {
// 		    out.println("mainServiceMap is null");
// 		}
// 		if (!mainServiceMap.containsKey("allCategoryDTOList")) {
// 		    out.println("allCategoryDTOList key is missing");
// 		}
		
		List<Category_auctionDTO> allCategoryDTOList = (List<Category_auctionDTO>)mainServiceMap.get("allCategoryDTOList");
// 		List<AuctionDTO> allAuctionDTOList = (List<AuctionDTO>)mainServiceMap.get("allAuctionDTOList");
		Map<Integer, AuctionDTO> allAuctionDTOMap = (Map<Integer, AuctionDTO>)mainServiceMap.get("allAuctionDTOMap");
		Map<Integer, Integer> highestBidMap = (Map<Integer, Integer>)mainServiceMap.get("highestBidMap");
		
		int categoryWidth = allCategoryDTOList.size();
		
		
		
	%>
	<section id="auction_container">
		<h1 id="auction_header">OTA 경매 물품 목록</h1>
		<form id="auction_search_form" method="get" action="auction/search">
			<select class="form-select" name="searchField">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="userName">닉네임</option>
			</select>
			<input type="text" maxlength="10" class="form-control" name="searchWord" value="<%=searchWord%>"/>
			<button type="submit" class="btn btn-secondary">검색하기</button>
		</form>
		
		<div class="category-banner">
<!-- 			<h5>카테고리</h5> -->
			<div class="wrap">
				<ul>
<%
					// 카테고리 개수만큼 <li>태그
					for(Category_auctionDTO categoryDTO : allCategoryDTOList){
%>
						<li style="width: <%=100/categoryWidth%>%;">
							<a href="auction/category?category_id=<%=categoryDTO.getCategory_auction_id()%>">
								<%=categoryDTO.getCategory_auction_name()%>
							</a>
						</li>
<%
					}
%>
				</ul>
			</div>
		</div>
		<div class="thumnail-list">
			<div class="wrap">
				<ul class="thumnail">
<%
					//클랏다 allAuctionDTOList를 Map으로 만들어야할 것같다...
					for(int key : highestBidMap.keySet()){
%>
							<li>
								<div class="thumnail-box">
									<div class="img-box">
										<a href="auction/detail?auction_id=<%=key%>">
											<img alt="안녕안녕" src="auction/img?<%=key%>">
										</a>
									</div>
									<div class="subject-box">
										<p class="subject">
											<a href="auction/detail?auction_id=<%=key%>"><%=allAuctionDTOMap.get(key).getAuction_title()%></a>
										</p>
										<p class="highest-bid">최고 입찰가: <%=highestBidMap.get(key) %></p>
									</div>
								</div>
							</li>
<%
					}
%>
				</ul>
			</div>
		</div>
		
		<h4>경매 물품 검색하기</h4>
		<form action="/auction/search" method="get">
			<input type="text" name="searchWord">
			<button type="submit">검색</button>
		</form>
		<hr>
		
		<h4>경매 물품 카테고리</h4>
		<button type="button" onclick="javascript:location.href='/auction/category?name=shoese';">신발</button>
		<button type="button" onclick="javascript:location.href='/auction/category?name=hat';">모자</button>
		<hr>
	
		<h4>현재 진행중인 경매</h4>
		<button type="button" onclick="javascript:location.href='/auction/card?name=나이키조던';">나이키 조던</button>
		<hr>
	</section>
	
	

	<%@ include file="../common/footer.jsp" %>
</body>
</html>