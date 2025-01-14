package ota.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ota.service.AuctionService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class AuctionController
 */
@WebServlet(urlPatterns = {"/auction", "/auction/card", "/auction/search", "/auction/category", "/auction/detail"})
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AuctionService auctionService = new AuctionService();
    @Override
    public void init() throws ServletException {
    	System.out.println("init() 실행됨!");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI().toString();
		String lastURI = requestURI.substring(requestURI.lastIndexOf("/"));
		
		System.out.println(lastURI);
		
//		if(lastURI.equals("/auction")) {
//			String searchField = Optional.ofNullable(request.getParameter("searchField")).orElse("");
//			String searchWord = Optional.ofNullable(request.getParameter("searchWord")).orElse("");
//			
//			Map<String,String> searchMap = new HashMap<String, String>();
//			searchMap.put("searchField", searchField);
//			searchMap.put("searchWord", searchWord);
//			request.getRequestDispatcher("/views/auction/auctionMain.jsp").forward(request, response);
//		}
//		else if(lastURI.equals("/card")) {
//			String viewPage = auctionService.cardService(request, response);
//			request.getRequestDispatcher(viewPage).forward(request, response);
//		}
//		else if(lastURI.equals("/search")) {
//			String viewPage = auctionService.searchService(request, response);
//			request.getRequestDispatcher(viewPage).forward(request, response);
//		}
//		else if(lastURI.equals("/category")) {
//			String viewPage = auctionService.categoryService(request, response);
//			request.getRequestDispatcher(viewPage).forward(request, response);
//		}
//		else if (lastURI.equals("/detail")) {
//			int auction_id = Integer.parseInt(request.getParameter("auction_id"));
//
//			request.setAttribute("auctionDTO", auctionService.getAuctionDTO(auction_id));
//			request.setAttribute("biddingDTOList", biddingDAO.selectListBidding(auction_id));
//			request.getRequestDispatcher("/views/auction/auctionDetail.jsp").forward(request, response);
//		}
		
		if (lastURI.equals("/auction")) {
//			Map<String, Object> mainServiceMap = new HashMap<String, Object>();
//			mainServiceMap.putAll(auctionService.mainService());
//			
//			request.setAttribute("mainServiceMap", mainServiceMap);
//			request.getRequestDispatcher("views/auction/auctionMain.jsp").forward(request, response);
			
			request.setAttribute("mainServiceMap", auctionService.mainService());
			request.getRequestDispatcher("/views/auction/auctionMain.jsp").forward(request, response);
		}
//		else if (lastURI.equals("/detail")) {
//			request.setAttribute("detailServiceMap", auctionService.detailService(Integer.parseInt(request.getParameter("auction_id"))));
//			request.getRequestDispatcher("views/auction/auctionDetail.jsp").forward(request, response);
//		}
		else if (lastURI.equals("/detail")) {
		    String auctionIdParam = request.getParameter("auction_id");
		    if (auctionIdParam == null) {
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing auction_id parameter");
		        return;
		    }
		    int auctionId = Integer.parseInt(auctionIdParam);
		    request.setAttribute("detailServiceMap", auctionService.detailService(auctionId));
		    request.getRequestDispatcher("/views/auction/auctionDetail.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
