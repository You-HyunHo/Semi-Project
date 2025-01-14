package ota.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ota.model.dto.AuctionDTO;
import ota.model.dao.AuctionDAO;
import ota.model.dto.BiddingDTO;
import ota.model.dao.BiddingDAO;
import ota.model.dto.Category_auctionDTO;
import ota.model.dao.Category_auctionDAO;

public class AuctionService {
	private AuctionDAO auctionDAO = new AuctionDAO();
	
//	
//	public String cardService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String selectedCardId = request.getParameter("id");
//		String selectedCardName = request.getParameter("name");
//		
//		request.setAttribute("id", selectedCardId);
//		request.setAttribute("name", selectedCardName);
//		
//		return "/views/auction/auctionDetail.jsp";
//	}
//	
//	public String searchService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String searchWord = request.getParameter("searchWord");
//		request.setAttribute("searchWord", searchWord);
//		
//		return "/views/auction/auctionSearchResultList.jsp";
//	}
//	
//	public String categoryService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String selectedCategoryName = request.getParameter("name");
//		request.setAttribute("name", selectedCategoryName);
//		
//		return "/views/auction/auctionCategoryList.jsp";
//	}
	
	// List<Category_auctionDTO> allCategoryDTOList,
	// List<AuctionDTO> allAuctionDTOList,
	// Map<Integer, Integer> highetBidMap 필요...
	// Map<String, Object>로 반환
	
	// 잠깐!! allCategoryDTOList를 Map<Integer, AuctionDTO>로 만들어야한다...
	// 이유: auctionMain.jsp에서 highestBid의 key(=auction_id)를 auctionDTO의 auction_id와 매칭시켜야 함
	public Map<String, Object> mainService() {
		Category_auctionDAO category_auctionDAO = new Category_auctionDAO();
		BiddingDAO biddingDAO = new BiddingDAO();
		
		List<Category_auctionDTO> allCategoryDTOList = new Vector<Category_auctionDTO>();
//		List<AuctionDTO> allAuctionDTOList = new Vector<Auc0tionDTO>();
		Map<Integer, AuctionDTO> allAuctionDTOMap = new HashMap<Integer, AuctionDTO>();
		Map<Integer, Integer> highestBidMap = new HashMap<Integer, Integer>();
		
		allCategoryDTOList = category_auctionDAO.categoryList();
		allAuctionDTOMap = auctionDAO.selectAllAuctionDTOMap();
		highestBidMap = biddingDAO.selectHighestBid(auctionDAO.selectAllAuction_idList());
		
		Map<String, Object> mainServiceMap = new HashMap<String, Object>();
		mainServiceMap.put("allCategoryDTOList", allCategoryDTOList);
		mainServiceMap.put("allAuctionDTOMap", allAuctionDTOMap);
		mainServiceMap.put("highestBidMap", highestBidMap);
		
		return mainServiceMap;
	}
	
	// detailService로 rename 후
	// auction_id에 맞는 AuctionDTO, List<BiddingDTO>를 Map<String, Object>으로 반환
	// Map<String, Object>의 엔트리는0 Controller가 request.setAttribue()로 세팅
	public Map<String, Object> detailService(int auction_id){
		BiddingDAO biddingDAO = new BiddingDAO();
		
		AuctionDTO auctionDTO = new AuctionDTO();
		List<BiddingDTO> biddingDTOList = new Vector<BiddingDTO>();
		
		Map<String, Object> detailServiceMap = new HashMap<String, Object>();
		
		auctionDTO = auctionDAO.selectAuctionDTO(auction_id);
		biddingDTOList = biddingDAO.selectListBidding(auction_id);
		
		detailServiceMap.put("auctionDTO", auctionDTO);
		detailServiceMap.put("biddingDTOList", biddingDTOList);
		
		System.out.println(detailServiceMap);

		return detailServiceMap;
	}

}
