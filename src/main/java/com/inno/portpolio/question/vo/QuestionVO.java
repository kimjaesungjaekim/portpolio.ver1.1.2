package com.inno.portpolio.question.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@EqualsAndHashCode(of="queNo")
public class QuestionVO {
	
	@NotBlank
	private String queNo;
	@NotBlank
	private String useId;
	@NotBlank
	private String queContent;
	@NotBlank
	private String queTitle;
	@NotBlank
	private String quePassword;
	
	private String queAnswer;
	
	@NotBlank
	private LocalDateTime  queDate;
	
	private LocalDateTime  queAnswerDate;
	
	private String fileNo;

}
