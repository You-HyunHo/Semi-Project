package ota.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ota.model.dto.AuctionDTO;

import static ota.common.DBConnection.*;

// DAO에는 create read update delete 기능이 포함되어야 한다!
// CRUD를 구현하자!
public class AuctionDAO{
	
	// 경매 물품 총 합계
	public int selectCountAuction(Map<String, Object> map) {
		int auctionCount = 0;
		String query = "SELECT count(*) FROM auction";
		
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			// 결과가 count(*) 한 줄 일테니까 next()를 한 번 해주는건가?
			rset.next();
			
			auctionCount = rset.getInt(1);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return auctionCount;
	}
	
	// 경매 물품 검색할 때 쓰일 예정
	// map의 { KEY:searchField, VALUE:searchWord } 중 KEY의 필드를 대상으로 VALUE값을 갖는 레코드 검색.
	public List<AuctionDTO> selectListAuction(Map<String, Object> map) {
		List<AuctionDTO> auctionDTOList = new Vector<AuctionDTO>();
		String query = "SELECT * FROM auction";
		
		if(!map.isEmpty() && map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		query += " ORDER BY auction_id DESC ";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				AuctionDTO dto = new AuctionDTO();
				
				dto.setAuction_id(rset.getInt("auction_id"));
				dto.setAuction_title(rset.getString("auction_title"));
				dto.setAuction_content(rset.getString("auction_content"));
				dto.setAuction_img(rset.getString("auction_img"));
				dto.setAuction_postdate(rset.getDate("auction_postdate"));
				dto.setAuction_startingbid(rset.getInt("auction_startingbid"));
				dto.setAuction_startingdate(rset.getDate("auction_startingdate"));
				dto.setAuction_enddate(rset.getDate("acution_enddate"));
				dto.setAuction_view(rset.getInt("auction_view"));
				dto.setAuction_winner(rset.getString("auction_winner"));
				dto.setUser_user_id(rset.getString("user_user_id"));
				
				auctionDTOList.add(dto);
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
		return auctionDTOList;
	}
	
	public List<AuctionDTO> selectListAuction() {
		List<AuctionDTO> auctionDTOList = new Vector<AuctionDTO>();
		String query = "SELECT * FROM auction";
		
		query += " ORDER BY auction_id DESC ";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				AuctionDTO dto = new AuctionDTO();
				
				dto.setAuction_id(rset.getInt("auction_id"));
				dto.setAuction_title(rset.getString("auction_title"));
				dto.setAuction_content(rset.getString("auction_content"));
				dto.setAuction_img(rset.getString("auction_img"));
				dto.setAuction_postdate(rset.getDate("auction_postdate"));
				dto.setAuction_startingbid(rset.getInt("auction_startingbid"));
				dto.setAuction_startingdate(rset.getDate("auction_startingdate"));
				dto.setAuction_enddate(rset.getDate("auction_enddate"));
				dto.setAuction_view(rset.getInt("auction_view"));
				dto.setAuction_winner(rset.getString("auction_winner"));
				dto.setUser_user_id(rset.getString("user_user_id"));
				
				auctionDTOList.add(dto);
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
		return auctionDTOList;
	}
	
	// 경매 물품 등록할 때 쓰일 예정
	public int insertAuction(AuctionDTO dto) {
		int result = 0;
		String query = "INSERT INTO auction ("
				+ " auction_id,"
				+ " auction_title,"
				+ " auction_content,"
				+ " auction_img,"
				+ " auction_postdate,"
				+ " auction_startingbid,"
				+ " auction_startingdate,"
				+ " auction_enddate,"
				+ " auction_view,"
				+ " auction_winner,"
				+ " user_user_id)"
				+ " VALUES("
				+ "seq_auction_auction_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getAuction_title());
			pstmt.setString(2, dto.getAuction_content());
			pstmt.setString(3, dto.getAuction_img());
			pstmt.setDate(4, dto.getAuction_postdate());
			pstmt.setInt(5, dto.getAuction_startingbid());
			pstmt.setDate(6, dto.getAuction_startingdate());
			pstmt.setDate(7, dto.getAuction_enddate());
			pstmt.setInt(8, dto.getAuction_view());
			pstmt.setString(9, dto.getAuction_winner());
			pstmt.setString(10, dto.getUser_user_id());
			
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	
	// 등록된 경매 물품 삭제할 때 쓰일 예정
	public int deleteAuction(int acution_id) {
		int result = 0;
		String query = "DELETE FROM auction WHERE auction_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, acution_id);
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	
	// 나중에 상황봐서 수정 필요..
	public int updateAuction(AuctionDTO dto) {
		int result = 0;
		String query = "UPDATE auction SET"
				+ " auction_id = ?,"
				+ " auction_title = ?,"
				+ " auction_content = ?,"
				+ " auction_img = ?,"
				+ " auction_postdate = ?,"
				+ " auction_startingbid = ?,"
				+ " auction_startingdate = ?,"
				+ " auction_enddate = ?,"
				+ " auction_view = ?,"
				+ " auction_winner = ?"
				+ " user_user_id = ?"
				+ " WHERE acution_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			// update대상 field의 type이 date, int, String 중 하나인데...
			// 특정 지어야 할 상황이 왔다...
			// 해결법: DB를 뜯어고치자, if문을 쓰자, DTO를 받자
			pstmt.setInt(1, dto.getAuction_id());
			pstmt.setString(2, dto.getAuction_title());
			pstmt.setString(3, dto.getAuction_content());
			pstmt.setString(4, dto.getAuction_img());
			pstmt.setDate(5, dto.getAuction_postdate());
			pstmt.setInt(6, dto.getAuction_startingbid());
			pstmt.setDate(7, dto.getAuction_startingdate());
			pstmt.setDate(8, dto.getAuction_enddate());
			pstmt.setInt(9, dto.getAuction_view());
			pstmt.setString(10, dto.getAuction_winner());
			pstmt.setString(11, dto.getUser_user_id());
			pstmt.setInt(12, dto.getAuction_id());
			
			result = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	
	public List<Integer> selectAllAuction_idList() {
		List<Integer> auction_idList = new Vector<Integer>();
		String query = "SELECT auction_id FROM auction";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				auction_idList.add(rset.getInt(1));
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
		return auction_idList;
	}
	
	public String selectTitleOfAuction_id(int auction_id) {
		String title = "";
		String query = "SELECT auction_title FROM auction"
				+ " WHERE auction_id = " + auction_id;
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			title = rset.getString("auction_title");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return title;
	}
	
	public AuctionDTO selectAuctionDTO(int auction_id) {
		AuctionDTO auctionDTO = new AuctionDTO();
		String query = "SELECT * FROM auction WHERE auction_id = " + auction_id;
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			
			auctionDTO.setAuction_id(auction_id);
			auctionDTO.setAuction_title(rset.getString("auction_title"));
			auctionDTO.setAuction_content(rset.getString("auction_content"));
			auctionDTO.setAuction_img(rset.getString("auction_img"));
			auctionDTO.setAuction_postdate(rset.getDate("auction_postdate"));
			auctionDTO.setAuction_startingdate(rset.getDate("auction_startingdate"));
			auctionDTO.setAuction_enddate(rset.getDate("auction_enddate"));
			auctionDTO.setAuction_view(rset.getInt("auction_view"));
			auctionDTO.setAuction_winner(rset.getString("auction_winner"));
			auctionDTO.setUser_user_id(rset.getString("user_user_id"));

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return auctionDTO;
	}
	
	public Map<Integer, AuctionDTO> selectAllAuctionDTOMap() {
		Map<Integer, AuctionDTO> allAuctionDTOMap = new HashMap<Integer, AuctionDTO>();
		String query = "SELECT * FROM auction";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				AuctionDTO auctionDTO = new AuctionDTO();
				
				auctionDTO.setAuction_id(rset.getInt("auction_id"));
				auctionDTO.setAuction_title(rset.getString("auction_title"));
				auctionDTO.setAuction_content(rset.getString("auction_content"));
				auctionDTO.setAuction_img(rset.getString("auction_img"));
				auctionDTO.setAuction_postdate(rset.getDate("auction_postdate"));
				auctionDTO.setAuction_startingbid(rset.getInt("auction_startingbid"));
				auctionDTO.setAuction_startingdate(rset.getDate("auction_startingdate"));
				auctionDTO.setAuction_enddate(rset.getDate("auction_enddate"));
				auctionDTO.setAuction_view(rset.getInt("auction_view"));
				auctionDTO.setAuction_winner(rset.getString("auction_winner"));
				auctionDTO.setUser_user_id(rset.getString("user_user_id"));
				
				allAuctionDTOMap.put(rset.getInt("auction_id"), auctionDTO);
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
		return allAuctionDTOMap;
	}
}
