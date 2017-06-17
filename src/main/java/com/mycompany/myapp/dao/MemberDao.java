package com.mycompany.myapp.dao;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;

@Component
public interface MemberDao {
	
	public String memberInsert(Member member);
	public Member memberSelect(String mid);
	public void memberUpdate(Member member);
	public void memberDelete(String mid);
}
