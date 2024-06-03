package com.example.myweb.board;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//어노테이션을 이용한 의존성 주입(DI),
	//런타임시에 spring framework에서객체를 생성해서 연결해준다.
	//다만 그냥 사용할 수 없고 해당 클래스에서 annotation을 정의해야한다.
	@Autowired
	BoardDAO dao;
	
	//목록 페이지로 forward
	//인자로 HttpServletRequest객체를 받아서 getParameter를 해도 된다.
	@RequestMapping(value="/board/list.do",method=RequestMethod.GET)
	public String boardList(Model model) {
		logger.info(">>GET - /board/list.do 요청 받음");
		
		List<BoardDTO>bList=dao.selectAll();
		logger.info(bList.toString());
		
		model.addAttribute("bList",bList);
		
		return "board/list";
	}
	
	//입력 페이지로 forward
	@RequestMapping(value="/board/insert.do",method=RequestMethod.GET)
	public String boardInsertPage() {
		logger.info(">>GET - /board/insert.do 요청 받음");
		return "board/insert";
	}
	
	//데이터 상세보기
	@RequestMapping(value="/board/detail.do",method=RequestMethod.GET)
	public String boardDetail(BoardDTO dto,Model model) {
		logger.info(">>POST - /board/detail.do 요청 받음");
		logger.info("요청한 상세정보 : "+dao.findBySeq(dto.getSeq()));
		model.addAttribute("bData",dao.findBySeq(dto.getSeq()));

		return "board/detail";
	}

	//데이터 삭제 후 목록으로 redirect
	@RequestMapping(value="/board/delete.do",method=RequestMethod.GET)
	public String boardDelete(int seq) {
		logger.info(">>GET - /board/delete.do 요청 받음");
		dao.delete(new BoardDTO(seq));
		return "redirect:list.do";
	}
	//데이터 저장 후 목록으로 redirect
	@RequestMapping(value="/board/insert.do",method=RequestMethod.POST)
	public String boardInsertData(BoardDTO dto) {
		logger.info(">>POST - /board/insert.do 요청 받음");
		
		dao.insert(dto);
		return "redirect:list.do";
	}
	//데이터 수정 후 목록으로 redirect
	@RequestMapping(value="/board/update.do",method=RequestMethod.POST)
	public String boardUpdateData(int seq,String title,String content) {
		logger.info(">>POST - /board/update.do 요청 받음");
		
		BoardDTO newData=dao.findBySeq(seq);
		newData.setTitle(title);
		newData.setContent(content);
		//수정한 경우 날짜에 m 표시로 수정했음을 나타낸다.
		newData.setRegDate("(m)"+LocalDate.now());
		dao.update(newData);
		
		return "redirect:list.do";
	}
}
