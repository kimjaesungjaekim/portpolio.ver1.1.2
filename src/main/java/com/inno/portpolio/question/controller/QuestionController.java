package com.inno.portpolio.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inno.portpolio.paging.BootstrapPaginationRenderer;
import com.inno.portpolio.paging.vo.PaginationInfo;
import com.inno.portpolio.paging.vo.SearchVO;
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
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	
	@GetMapping("/main")
	public String mainPage(
//			 @ModelAttribute("simpleCondition") SearchVO simpleCondition
//			,@RequestParam(value="page", required = false, defaultValue = "1") int currentPage
//			,Model model
		) {
		
//		PaginationInfo<QuestionVO> paging = new PaginationInfo<>();
//		
//		paging.setSimpleCondition(simpleCondition);
//		paging.setCurrentPage(currentPage);
//		
//		questionService.retrieveQuestionList(paging);
//		
//		paging.setRenderer(new BootstrapPaginationRenderer());
//		
//		model.addAttribute("paging", paging);
		
		return "question/questionMain";
	}
}
