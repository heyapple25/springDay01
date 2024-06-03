package com.example.myweb.board;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	public static final List<BoardDTO> bList=new ArrayList<BoardDTO>();
	public static int seq=1;
	static {
		bList.add(new BoardDTO(seq++,"test1","글 1쓰기 연습","글 쓰기1 내용",LocalDate.now().toString(),0));
		bList.add(new BoardDTO(seq++,"test2","글 2쓰기 연습","글 쓰기2 내용",LocalDate.now().toString(),0));
		bList.add(new BoardDTO(seq++,"test3","글 쓰3기 연습","글 쓰기 43내용",LocalDate.now().toString(),0));
		bList.add(new BoardDTO(seq++,"test4","글 쓰기4 연습","글 쓰기 내5용",LocalDate.now().toString(),0));
		bList.add(new BoardDTO(seq++,"tes5t","글 쓰기5 연습","글 쓰기 내4용",LocalDate.now().toString(),0));
		bList.add(new BoardDTO(seq++,"tes6t","글 쓰기6 연습","글 쓰기6 내용",LocalDate.now().toString(),0));		
	}
	public List<BoardDTO>selectAll(){
		return bList;
	}
	public BoardDTO findBySeq(int seq){
		int idx=bList.indexOf(new BoardDTO(seq));
		if(idx!=-1) {
			return bList.get(idx);
		}
		return null;
	}
	public void insert(BoardDTO dto) {
		dto.setSeq(seq++);
		LocalDate now=LocalDate.now();
		dto.setRegDate(now.toString());
		bList.add(dto);
	}
	
	public void update(BoardDTO dto) {
		BoardDTO old=findBySeq(dto.getSeq());
		old=dto;
	}
	public void delete(BoardDTO dto) {
		bList.remove(bList.indexOf(dto));
	}
}
