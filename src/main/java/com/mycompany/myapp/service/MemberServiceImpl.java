package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.MemberDao;
import com.mycompany.myapp.dto.Member;

@Component
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao dao;
	
	@Override
	public String memberJoin(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member memberChoose(String mid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void memberRevise(String mid) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void memberWidraw(String mid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkMpassword(String mid) {
		// TODO Auto-generated method stub
		
	}

	

}
