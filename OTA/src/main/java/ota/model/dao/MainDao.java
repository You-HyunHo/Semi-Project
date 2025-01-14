package ota.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ota.common.DBConnection;
import ota.error.CustomException;
import ota.error.type.NoticeError;
import ota.model.dto.AuctionDTO;
import ota.model.dto.NoticeDto;

import static ota.common.DBConnection.*;
public class MainDao {

	public List<NoticeDto> noticeList() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeDto> noticeList = new ArrayList<NoticeDto>();
		String query = "select notice_id, notice_title "
				+ " from notice "
				+ " order by notice_id desc "
				+ " limit 5";
		try {
			pstmt =conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setNoticeId(rset.getInt(1));
				noticeDto.setNoticeTitle(rset.getString(2));
				
				noticeList.add(noticeDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}

		return noticeList;
	}

	public List<AuctionDTO> hightPriceAuctionList() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<AuctionDTO> hightPriceAuctionList = new ArrayList<AuctionDTO>();
		String query = "select notice_id, notice_title "
				+ " from notice "
				+ " order by notice_id desc "
				+ " limit 5";
		try {
			pstmt =conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				AuctionDTO auctionDTO = new AuctionDTO();
				
				hightPriceAuctionList.add(auctionDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}

		return hightPriceAuctionList;
	}

	public List<AuctionDTO> popularAuctionList() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<AuctionDTO> hightPriceAuctionList = new ArrayList<AuctionDTO>();
		String query = "select notice_id, notice_title "
				+ " from notice "
				+ " order by notice_id desc "
				+ " limit 5";
		try {
			pstmt =conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				AuctionDTO auctionDTO = new AuctionDTO();
				
				hightPriceAuctionList.add(auctionDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}

		return hightPriceAuctionList;
	}

}
