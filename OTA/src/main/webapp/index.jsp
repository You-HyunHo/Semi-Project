<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ota.model.dto.NoticeDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script defer src="/views/common/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
*{
	font-family: sans-serif;
}

#ota-main {
	margin: 0;
	padding: 0;
	/* 	background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.8)),
		url("views/common/img/hammer-7286346_1920.png");
	background-repeat: no-repeat;
	background-size: 100% 100%; */
	
}

#main-content-section {
	margin: 0 auto;
	width: 80%;
	display: flex;
	justify-content: space-between;
}

#mcs-box1 {
	width: 400px;
}

#mcs-box2 {
	width: 400px;
}

#mcs-box2-auction div:first-child {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.anl-noview {
	display: none;
}

#mcs-box2-newNotice div:first-child {
	display: flex;
	justify-content: space-between;
}
#faq-section {
	margin: 0 auto;
	width: 60%;
}
#faq-section h2 {
	text-align: center;
}

#question-section h2 {
	text-align: center;
}
</style>
</head>
<body>
	<%
	List<NoticeDto> noticeList = (List<NoticeDto>) request.getAttribute("noticeList");
	%>
	<%@include file="./views/common/header.jsp"%>
	<main id='ota-main'>
	<div class="bg-light">
	<br/>
		<section id="main-content-section" class="p-3">
			<div id='mcs-box1'>
				<button id="abc" onclick="abc();">인기있는 경매</button>
			</div>
			<div id='mcs-box2'>
				<div id="mcs-box2-auction"
					class="mcs-box2-item p-2 rounded-2 bg-secondary bg-opacity-10">
					<div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a class="auction-nav nav-link active"
								href="javascript:auctionNavToggle();">최고가 경매</a></li>
							<li class="nav-item"><a class="auction-nav nav-link"
								href="javascript:auctionNavToggle();">인기 경매</a></li>
						</ul>
						<a class="link-secondary p-2" href='/notice'>더보기</a>
					</div>
					<div class="autcion-nav-list p-2 pb-0">
						<%
						if (noticeList.isEmpty()) {
						%>
						<p class="text-secondary">최근 공지사항이 없습니다.</p>
						<%
						} else {
						for (NoticeDto noticeDto : noticeList) {
							String noTitle = "";
							if (noticeDto.getNoticeTitle().length() > 20) {
								noTitle = noticeDto.getNoticeTitle().substring(0, 20) + "...";
							} else {
								noTitle = noticeDto.getNoticeTitle();
							}
						%>
						<p>
							<a class="link-secondary link-underline-opacity-0"
								href="/notice/view?noticeId=<%=noticeDto.getNoticeId()%>">-
								<%=noTitle%></a>
						</p>
						<%
						}
						}
						%>
					</div>
					<div class="autcion-nav-list p-2 pb-0 anl-noview">
												<%
						if (noticeList.isEmpty()) {
						%>
						<p class="text-secondary">최근 공지사항이 없습니다.</p>
						<%
						} else {
						for (NoticeDto noticeDto : noticeList) {
							String noTitle = "";
							if (noticeDto.getNoticeTitle().length() > 20) {
								noTitle = noticeDto.getNoticeTitle().substring(0, 20) + "...";
							} else {
								noTitle = noticeDto.getNoticeTitle();
							}
						%>
						<p>
							<a class="link-secondary link-underline-opacity-0"
								href="/notice/view?noticeId=<%=noticeDto.getNoticeId()%>">-
								<%=noTitle%></a>
						</p>
						<%
						}
						}
						%>
					</div>
				</div>
				<br />
				<div id="mcs-box2-newNotice"
					class="mcs-box2-item p-3 pb-1 rounded-2 bg-secondary bg-opacity-10">
					<div>
						<p>최근 공지</p>
						<a class="link-secondary" href='/notice'>더보기</a>
					</div>
					<div>
						<%
						if (noticeList.isEmpty()) {
						%>
						<p class="text-secondary">최근 공지사항이 없습니다.</p>
						<%
						} else {
						for (NoticeDto noticeDto : noticeList) {
							String noTitle = "";
							if (noticeDto.getNoticeTitle().length() > 20) {
								noTitle = noticeDto.getNoticeTitle().substring(0, 20) + "...";
							} else {
								noTitle = noticeDto.getNoticeTitle();
							}
						%>
						<p>
							<a class="link-secondary link-underline-opacity-0"
								href="/notice/view?noticeId=<%=noticeDto.getNoticeId()%>">-
								<%=noTitle%></a>
						</p>
						<%
						}
						}
						%>
					</div>
				</div>
			</div>
		</section>
		</div>
		<br/>
		<br/>
		<section id="faq-section">
			<h2><strong>자주 묻는 질문</strong></h2><br/>
			<div class="accordion accordion-flush" id="accordionFlushExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingOne">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
							aria-expanded="false" aria-controls="flush-collapseOne">
							Accordion Item #1</button>
					</h2>
					<div id="flush-collapseOne" class="accordion-collapse collapse"
						aria-labelledby="flush-headingOne">
						<div class="accordion-body">
							1
						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingTwo">
						<button class="accordion-button collapsed btn-primary" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo"
							aria-expanded="false" aria-controls="flush-collapseTwo">
							Accordion Item #2</button>
					</h2>
					<div id="flush-collapseTwo" class="accordion-collapse collapse"
						aria-labelledby="flush-headingTwo">
						<div class="accordion-body">
							2
						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingThree">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseThree"
							aria-expanded="false" aria-controls="flush-collapseThree">
							Accordion Item #3</button>
					</h2>
					<div id="flush-collapseThree" class="accordion-collapse collapse"
						aria-labelledby="flush-headingThree">
						<div class="accordion-body">
							3
						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingfour">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapsefour"
							aria-expanded="false" aria-controls="flush-collapsefour">
							Accordion Item #4</button>
					</h2>
					<div id="flush-collapsefour" class="accordion-collapse collapse"
						aria-labelledby="flush-headingfour">
						<div class="accordion-body">
							4
						</div>
					</div>
				</div>
			</div>
		</section>
		<br/>
		<br/>
		<div class="bg-light">
		<section id="question-section" class="p-5">
			<h2><strong>1:1 문의하기</strong></h2>
			<br/>
			<p class="fs-4 text-center">
				<a class="link-secondary link-underline-opacity-50"
					href="/contactUs/writeForm"><i>contact us</i></a>
			</p>
		</section>
		</div>
	</main>
	<%@include file="./views/common/footer.jsp"%>
<script type="text/javascript">
	function auctionNavToggle(){
		const navItems = document.getElementsByClassName("auction-nav");
		const listItems = document.getElementsByClassName("autcion-nav-list");
		for (var i = 0; i < 2; i++) {
			navItems[i].classList.toggle("active");
			listItems[i].classList.toggle("anl-noview");
		}
	}
	
</script>	
</body>
</html>