package ota.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ota.model.dto.AuctionDTO;
import ota.model.dto.NoticeDto;
import ota.service.MainService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/welcome")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MainService mainService = new MainService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		List<NoticeDto> noticeList = mainService.noticeList();
		List<AuctionDTO> hightPriceAuctionList = mainService.hightPriceAuctionList();
		List<AuctionDTO> popularAuctionList = mainService.popularAuctionList();
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("hightPriceAuctionList", hightPriceAuctionList);
		request.setAttribute("popularAuctionList", popularAuctionList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
