package com.inno.portpolio.question.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inno.portpolio.paging.vo.PaginationInfo;
import com.inno.portpolio.question.mapper.QuestionMapper;
import com.inno.portpolio.question.service.QuestionService;
import com.inno.portpolio.question.vo.QuestionVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {
	
	private final QuestionMapper questionMapper;
	
	
	// 질문사항 리스트 페이징 처리
	@Override
	public void retrieveQuestionList(PaginationInfo<QuestionVO> paging) {
		
//		int totalRecord = questionMapper.selectQuestionTotalRecord(paging);
		
//		paging.setTotalRecord(totalRecord);
		
//		List<QuestionVO> dataList = questionMapper.selectQuestionList(paging);
//		
//		paging.setDataList(dataList);
		
	}

}
