package ota.model.dao;

import static ota.common.DBConnection.close;
import static ota.common.DBConnection.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ota.model.dto.BiddingDTO;

public class BiddingDAO {
	
	
	public List<BiddingDTO> selectListBidding(Map<String, String> map) {
		List<BiddingDTO> biddingDTOList = new Vector<BiddingDTO>();
		String query = "SELECT * FROM bidding";
		
		if(map != null && map.get("searchField") != null) {
			query += " WHERE " + map.get("searchField") + "=" + map.get("searchWord"); 
		}
		query += " ORDER BY bidding_id";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				BiddingDTO dto = new BiddingDTO();
				dto.setBidding_id(rset.getInt("bidding_id"));
				dto.setBidding_bid(rset.getInt("bidding_bid"));
				dto.setBidding_biddate(rset.getDate("bidding_biddate"));
				dto.setUser_user_id(rset.getString("user_user_id"));
				dto.setAuction_auction_id(rset.getInt("auction_auction_id"));
				
				biddingDTOList.add(dto);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return biddingDTOList;
	}
	
	public List<BiddingDTO> selectListBidding(int auction_id) {
		List<BiddingDTO> biddingDTOList = new Vector<BiddingDTO>();
		String query = "SELECT * FROM bidding WHERE auction_auction_id = " + auction_id + " ORDER BY bidding_id";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				BiddingDTO dto = new BiddingDTO();
				dto.setBidding_id(rset.getInt("bidding_id"));
				dto.setBidding_bid(rset.getInt("bidding_bid"));
				dto.setBidding_biddate(rset.getDate("bidding_biddate"));
				dto.setUser_user_id(rset.getString("user_user_id"));
				dto.setAuction_auction_id(rset.getInt("auction_auction_id"));
				
				biddingDTOList.add(dto);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return biddingDTOList;
	}
	
//	//보완 필요
//	public BiddingDTO selectHighestBiddingDTO(Map<String, String> map) {
//		BiddingDTO highestBidDTO = new BiddingDTO();
//		String query = "SELECT * FROM bidding";
//		
//		if(map != null && map.get("searchField") != null) {
//			query += " WHERE " + map.get("searchField") + "=" + map.get("searchWord");
//		}
//		query += "ORDER BY bidding_bid ";
//		
//		return highestBidDTO;
//	}
	
	public Map<Integer, Integer> selectHighestBid(List<Integer> auction_idList) {
		Map<Integer, Integer> highestBidMap = new HashMap<Integer, Integer>();
		String baseQuery = "SELECT MAX(bidding_bid) FROM bidding";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			for(int auction_id : auction_idList) {
				String query = baseQuery + " WHERE auction_auction_id = " + auction_id;
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				rset.next();
				highestBidMap.put(auction_id, rset.getInt(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();	
		}
		finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return highestBidMap;
	}
}
