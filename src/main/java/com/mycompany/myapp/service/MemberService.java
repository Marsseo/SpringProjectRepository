package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;

@Component
public interface MemberService {
	
	public String memberJoin(Member member);
	public Member memberChoose(String mid);
	public void memberRevise(String mid);
	public void memberWidraw(String mid);
	public void checkMpassword(String mid);
}
