package com.inno.portpolio.question.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.inno.portpolio.paging.vo.PaginationInfo;
import com.inno.portpolio.question.vo.QuestionVO;


/**
* @author 연구개발 5팀 사원 김재성
* @since 2024. 03. 11.
* @version 1.0
* @see javax.servlet.http.HttpServlet 
* <pre>
* [[개정이력(Modification Information)]]
*    수정일            수정자               수정내용
* ------------     --------    ----------------------
* 2024.03.11.        김재성       최초작성
* Copyright (c) 2024 by INNOVATION-T All right reserved
* </pre>
*/

@Mapper
public interface QuestionMapper {
	
	/**
	 * Qna 질문사항 리스트
	 * 페이징 처리
	 * @return
	 */
	public List<QuestionVO> selectQuestionList(PaginationInfo<QuestionVO> paging);
	
	/**
	 * 총 레코드수 연산
	 * @param paging
	 * @return
	 */
	public int selectQuestionTotalRecord(PaginationInfo<QuestionVO> paging);
	
}
