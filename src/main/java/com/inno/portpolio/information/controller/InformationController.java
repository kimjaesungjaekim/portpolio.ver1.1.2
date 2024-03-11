package com.inno.portpolio.information.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inno.portpolio.common.enumpkg.ServiceResult;
import com.inno.portpolio.information.service.InformationService;
import com.inno.portpolio.information.vo.CareerVO;
import com.inno.portpolio.information.vo.CertificateVO;
import com.inno.portpolio.information.vo.SchoolVO;
import com.inno.portpolio.information.vo.SkilVO;
import com.inno.portpolio.user.mapper.UsersMapper;
import com.inno.portpolio.user.vo.UsersVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
* @author 연구개발 5팀 사원 김재성
* @since 2024. 03. 08.
* @version 1.0
* @see javax.servlet.http.HttpServlet 
* <pre>
* [[개정이력(Modification Information)]]
*    수정일            수정자               수정내용
* ------------     --------    --------------------------------------------------
* 2024.03.08.        김재성       최초작성
* 2024.03.11.        김재성       프로필 정보 경력,학력,기술,자격증 추가 삭제 기능 구현
* Copyright (c) 2024 by INNOVATION-T All right reserved
* </pre>
*/
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/information")
public class InformationController {
	
	private final UsersMapper usersMapper;
	
	private final InformationService informationService;
	
	
	@GetMapping("/main")
	public String mainPage(
			Authentication auth
			,Model model
			) {
		
		String useId = auth.getName();
		
//		System.out.println("인증 객체 아이디 : " + useId);
		
		Map<String, Object> users = usersMapper.loadUserByUsername(useId);
		
		List<SchoolVO> schoolList = informationService.retrieveSchoolInformation();
		List<CareerVO> careerList = informationService.retrieveCareerInformation();
		List<CertificateVO> certificateList = informationService.retrieveCertificateInformation();
		List<SkilVO> skilList = informationService.retrieveSkilInformation();
		
		log.info("인증 권한 로그인 된 아이디 값  확인 : {}",useId);
		log.info("개인 정보 조회 객체 확인 : {}",users);
		log.info("학력 객체 확인 : {}",schoolList);
		log.info("경력 객체 확인 : {}",careerList);
		log.info("자격증 객체 확인 : {}",certificateList);
		log.info("기술 객체 확인 : {}",skilList);
		
		model.addAttribute("users",users);
		model.addAttribute("schoolInfoList",schoolList);
		model.addAttribute("careerList",careerList);
		model.addAttribute("certificateList",certificateList);
		model.addAttribute("skilList",skilList);
		
		return "information/informationMain";
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public HashMap<String, Object> profileUpdate(
		 @RequestBody UsersVO user	
		,Authentication auth
		){
		
		user.setUseId(auth.getName());
		
		ServiceResult res;
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	try {
    		 res = informationService.modifyProfileInformation(user);
    		 
    		 if(res.equals(ServiceResult.OK)) {
    			 map.put("result",res);
    			 map.put("message","프로필 변경 성공 ");
    		 }else {
    			 map.put("result",res);
    			 map.put("message","프로필 변경 실패 "); 
    		 }
    		 
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return map;
	};
	
	@PostMapping("/career/add")
	@ResponseBody
	public HashMap<String, Object> careerCreateInformation(
			@RequestBody CareerVO career	
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			res = informationService.createCareer(career);
			
			if(res.equals(ServiceResult.OK)) {
				map.put("result",res);
				map.put("message","경력 추가 성공 !");
			}else {
				map.put("result",res);
				map.put("message","경력 추가 실패 !"); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	};
	
	@PostMapping("/career/remove")
	@ResponseBody
	public HashMap<String, Object> careerRemoveInformation(
			@RequestBody List<String> careerVaules
		){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CareerVO career = new CareerVO();
		
		try {
			for(String carNo : careerVaules) {
				log.info("carNo 데이터 확인 : {}", carNo);
				
				career.setCarNo(carNo);
				career.setInfoNo("CA");
				res = informationService.removeCareer(career);
				
				if(res.equals(ServiceResult.OK)) {
					map.put("result",res);
					map.put("message","경력 삭제 성공 !");
				}else {
					map.put("result",res);
					map.put("message","경력 삭제 실패 !"); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@PostMapping("/school/add")
	@ResponseBody
	public HashMap<String, Object> schoolCreateInformation(
			@RequestBody SchoolVO school	
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			res = informationService.createSchool(school);
			
			if(res.equals(ServiceResult.OK)) {
				map.put("result",res);
				map.put("message","학력 추가 성공 !");
			}else {
				map.put("result",res);
				map.put("message","학력 추가 실패 !"); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	};
	
	@PostMapping("/school/remove")
	@ResponseBody
	public HashMap<String, Object> schoolRemoveInformation(
			@RequestBody List<String> schoolValues
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		SchoolVO school = new SchoolVO();
		
		try {
			for(String schNo : schoolValues) {
				log.info("carNo 데이터 확인 : {}", schNo);
				
				school.setSchNo(schNo);
				school.setInfoNo("SC");
				res = informationService.removeSchool(school);
				
				if(res.equals(ServiceResult.OK)) {
					map.put("result",res);
					map.put("message","학력 삭제 성공 !");
				}else {
					map.put("result",res);
					map.put("message","학력 삭제 실패 !"); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@PostMapping("/certificate/add")
	@ResponseBody
	public HashMap<String, Object> certificateCreateInformation(
			@RequestBody CertificateVO certificate	
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			res = informationService.createCertificate(certificate);
			
			if(res.equals(ServiceResult.OK)) {
				map.put("result",res);
				map.put("message","자격증 추가 성공 !");
			}else {
				map.put("result",res);
				map.put("message","자격증 추가 실패 !"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	};
	
	@PostMapping("/certificate/remove")
	@ResponseBody
	public HashMap<String, Object> certificateRemoveInformation(
			@RequestBody List<String> certificateValues
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CertificateVO certificate = new CertificateVO();
		
		try {
			for(String cerNo : certificateValues) {
				log.info("carNo 데이터 확인 : {}", cerNo);
				
				certificate.setCerNo(cerNo);
				certificate.setInfoNo("CE");
				res = informationService.removeCertificate(certificate);
				
				if(res.equals(ServiceResult.OK)) {
					map.put("result",res);
					map.put("message","자격증 삭제 성공 !");
				}else {
					map.put("result",res);
					map.put("message","자격증 삭제 실패 !"); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@PostMapping("/skil/add")
	@ResponseBody
	public HashMap<String, Object> skilCreateInformation(
			@RequestBody SkilVO skil	
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			res = informationService.createSkil(skil);
			
			if(res.equals(ServiceResult.OK)) {
				map.put("result",res);
				map.put("message","기술스킬 추가 성공 !");
			}else {
				map.put("result",res);
				map.put("message","기술스킬 추가 실패 !"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	};
	
	@PostMapping("/skil/remove")
	@ResponseBody
	public HashMap<String, Object> skilRemoveInformation(
			@RequestBody List<String> skilValues
			){
		
		ServiceResult res;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		SkilVO skil = new SkilVO();
		
		try {
			for(String skilNo : skilValues) {
				log.info("carNo 데이터 확인 : {}", skilNo);
				
				skil.setSkilNo(skilNo);
				skil.setInfoNo("SK");
				res = informationService.removeSkil(skil);
				
				if(res.equals(ServiceResult.OK)) {
					map.put("result",res);
					map.put("message","기술스킬 삭제 성공 !");
				}else {
					map.put("result",res);
					map.put("message","기술스킬 삭제 실패 !"); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
}
