package com.kh.redding.board.model.dao;

import static com.kh.redding.common.JDBCTemplate.close;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.redding.board.model.vo.Board;
import com.kh.redding.board.model.vo.BoardPageInfo;

public class BoardDao {
	private Properties prop = new Properties();
	public BoardDao() {
		
		String fileName = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//커뮤니티 게시글 목록 조회용
	public ArrayList<HashMap<String, Object>> selectBoardList(Connection con, BoardPageInfo pi) {
		PreparedStatement pstmt = null;
		ArrayList<HashMap<String, Object>> list = null;
		HashMap<String, Object> hmap = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectBoardList");
//		System.out.println(query);
		
		//조회 시작할 행 번호와 마지막 행 번호 계산
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()) {
				hmap = new HashMap<String, Object>();
				
				hmap.put("bid", rset.getInt("BID"));
				hmap.put("btitle", rset.getString("BTITLE"));
				hmap.put("bwriter", rset.getString("NICK_NAME"));
				hmap.put("bdate", rset.getDate("BDATE"));
				hmap.put("bcount", rset.getInt("BCOUNT"));
				hmap.put("blike", rset.getInt("BLIKE"));
				
				list.add(hmap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	//게시물 수 조회용 메소드
	public int getListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("listCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}

	public Board selectOne(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				
				b.setBid(rset.getInt("BID"));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBwriter((rset.getInt("NICK_NAME")));
				b.setBdate(rset.getDate("BDATE"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBlike(rset.getInt("BLIKE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return b;
	}

	public int updateCount(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bid);
			pstmt.setInt(2, bid);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//공지사항 insert
	public int insertNotice(Connection con, String noticeTitle, String noticeContent, int writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, noticeTitle);
			pstmt.setInt(2, writer);
			pstmt.setString(3 , noticeContent);
			pstmt.setString(4, "공지사항");
			
			result = pstmt.executeUpdate();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//공지사항 리스트 가져오기
	public ArrayList<HashMap<String, Object>> selectNotice(Connection con, BoardPageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String,Object>> noticeList = null;
		HashMap<String , Object> hmap = null;
		
		
		String query = prop.getProperty("selectNotice");
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			
			String type = "공지사항";
			
			pstmt.setString(1, type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			
			noticeList = new ArrayList<HashMap<String,Object>>();
			
			while(rset.next()) {
				hmap = new HashMap<String , Object>();
				Board board = new Board();
				
				int num = rset.getInt("ROWNUM");
				board.setBid(rset.getInt("BID"));
				board.setBtitle(rset.getString("BTITLE"));
				board.setBwriter(rset.getInt("BWRITER"));
				String name = rset.getString("MNAME");
				board.setBdate(rset.getDate("BDATE"));
				board.setBcount(rset.getInt("BCOUNT"));
				board.setBcontent(rset.getString("BCONTENT"));
				board.setBdivision(rset.getString("BDIVISION"));
				board.setBmodify_date(rset.getDate("BMODIFY_DATE"));
				
				hmap.put("num", num);
				hmap.put("board", board);
				hmap.put("name" , name);
				
				noticeList.add(hmap);
			
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return noticeList;
	}

	//공지사항 몇개 인지 count하는 함수
	public int selectNoticeListCount(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("selectNoiticeCount");
		
		try {
			pstmt = con.prepareStatement(query);
			
			String type = "공지사항";
			
			pstmt.setString(1, type);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return result;
	}

	
	//공지사항 상세
	public Board selectNoticeOne(Connection con, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board selectNotice = null;
		
		String query = prop.getProperty("selectOneNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, bid);
			pstmt.setString(2, "관리자");
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				selectNotice = new Board();
				
				selectNotice.setBid(rset.getInt("BID"));
				selectNotice.setBtitle(rset.getString("BTITLE"));
				selectNotice.setBdate(rset.getDate("BDATE"));
				selectNotice.setBcount(rset.getInt("BCOUNT"));
				selectNotice.setBcontent(rset.getString("BCONTENT"));
				selectNotice.setBdivision(rset.getString("BDIVISION"));
				selectNotice.setBmodify_date(rset.getDate("BMODIFY_DATE"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		 
		
		return selectNotice;
	}

	public int UpdateNotice(Connection con, int bid, String btitle, String bcontent) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, btitle);
			pstmt.setString(2, bcontent);
			pstmt.setInt(3, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	//조회 수 조회
	public int selectNoticeCount(Connection con, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int bcount = 0;
		
		String query = prop.getProperty("selectNoticeCount");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, bid);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				bcount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return bcount;
	}

	//조회수 증가
	public int updateNoticeCount(Connection con, int bid, int count) {
		PreparedStatement pstmt =null;
		int result = 0;
		
		String query = prop.getProperty("updateNoticeCount");
		
		try {
			pstmt = con.prepareStatement(query);
			
			count = count+1;
			
			pstmt.setInt(1, count);
			pstmt.setInt(2, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//공지사항 한개 삭제
	public int deleteNoticeOne(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("DeleteNoticeOne");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public int insertFAQ(Connection con, String noticeTitle, String noticeContent, int writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFAQ");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, noticeTitle);
			pstmt.setInt(2, writer);
			pstmt.setString(3 , noticeContent);
			pstmt.setString(4, "FAQ");
			
			result = pstmt.executeUpdate();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//자주하는 질문 총 갯수
	public int selectFAQListCount(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("selectNoiticeCount");
		
		try {
			pstmt = con.prepareStatement(query);
			
			String type = "FAQ";
			
			pstmt.setString(1, type);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	
		
		return result;
	}

	//자주하는 질문 리스트 
	public ArrayList<Board> selectFAQList(Connection con, BoardPageInfo pi) {
		PreparedStatement pstmt = null;
		ArrayList<Board> FAQList = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectFAQList"); 
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		try {
			pstmt= con.prepareStatement(query);
			
			String type = "FAQ";
			
			pstmt.setString(1, type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			FAQList = new ArrayList<Board>();
			while(rset.next()) {
				Board board = new Board();
				
				board.setBid(rset.getInt("BID"));
				board.setBtitle(rset.getString("BTITLE"));
				board.setBdate(rset.getDate("BDATE"));
				board.setBwriter(rset.getInt("BWRITER"));
				board.setBcontent(rset.getString("BCONTENT"));
				board.setBdivision(rset.getString("BDIVISION"));
				board.setBmodify_date(rset.getDate("BMODIFY_DATE"));
				
				FAQList.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return FAQList;
	}


}
