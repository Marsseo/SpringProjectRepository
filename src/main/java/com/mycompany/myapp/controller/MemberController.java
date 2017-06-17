package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value="/member/newMember", method=RequestMethod.GET)
	public String newMemberGet(Member member){
		
				
		return "member/newMember";
	}
	
	@RequestMapping(value="/member/newMember", method=RequestMethod.POST)
	public String newMemberPost(Member member){
		
		member.setMoriginalimgname(member.getMattach().getOriginalFilename());
		member.setMimgtype(member.getMattach().getContentType());
		String fileName = new Date().getTime()+"-"+member.getMoriginalimgname();
		member.setMsavedimgname(fileName);
		
		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");	
		File file = new File(realPath+fileName);
		try {member.getMattach().transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		service.memberJoin(member);
		
		return "member/newMember";
	}
	
	
}
