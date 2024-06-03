## 과제

과제 : Servlet으로 구현한 게시판을 스프링 프레임워크에 적용해서 CRUD 기능을 구현 해 보세요.

## 과제 결과
![ezgif-6-58f1d20f32](https://github.com/heyapple25/springDay01/assets/56960059/d49629cb-b7eb-4862-8319-7cedd90e1ddc)


조회수 기능, 추가, 삭제, 수정(수정시 날짜는 (m) 표시를 해줌) 기능을 추가했다.

---

## 발생했던 오류
- 한글 깨짐

값을 POST로 넘기는 과정에서 한글이 깨지는 현상이 발생했다.

characterEncodingFilter는 한글이 깨지지 않도록 Encoding Character Set을 처리해주는 fileter이다. web.xml에 다음과 같이 추가해준다.
```xml
	<filter>       
		 <filter-name>encodingFilter</filter-name>        
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    <init-param>            
			<param-name>encoding</param-name>            
			<param-value>UTF-8</param-value>        
		</init-param>
	</filter>
	<filter-mapping>        
		<filter-name>encodingFilter</filter-name>        
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```
## 추가로 알게된 점
- 인자로 값을 받을 때 name속성을 이용해서 받을 수도 있다.
![12512512](https://github.com/heyapple25/springDay01/assets/56960059/ed320ac5-1138-4a8e-a3c3-1c7d88ab60da)
- [README.md 작성 시 참고](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
- ${\textsf{\color{red}git push가 안되길래 -f 옵션을 지정해버리니까 전에 올렸던 README.md가 증발했다. 다음에는 신중하게 push하자....}}$
---

##### BoardController.java
```java
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
		
		//상세보기를 누르면 조회수가 +1되어야 함
		dao.cntUpdate(dto);
		
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


```
##### BoardDAO.java
```java
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
	
	public void cntUpdate(BoardDTO dto) {
		BoardDTO cntBefore=bList.get(bList.indexOf(dto));
		cntBefore.setCnt(cntBefore.getCnt()+1);
	}
}
```
