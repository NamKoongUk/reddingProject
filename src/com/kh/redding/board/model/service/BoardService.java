package com.kh.redding.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.redding.board.model.dao.BoardDao;
import com.kh.redding.board.model.vo.Board;
import com.kh.redding.product.model.vo.PageInfo;
import static com.kh.redding.common.JDBCTemplate.*;

public class BoardService {
	
	/*//게시물 목록 조회
	public ArrayList<Board> selectList(PageInfo pi) {
		Connection con = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(con, pi);
		
		close(con);
		
		return list;
	}*/
	
	//게시물 수 조회용 메소드
	public int getListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<HashMap<String, Object>> selectBoardList() {
		Connection con = getConnection();
		
		ArrayList<HashMap<String, Object>> list = new BoardDao().selectBoardList(con);
		for(int i=0; i<list.size();i++) {
			
			System.out.println(list.get(i));
		}
		
		close(con);
		
		return list;
	}

	

}
