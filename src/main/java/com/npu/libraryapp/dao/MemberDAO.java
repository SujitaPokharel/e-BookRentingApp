package com.npu.libraryapp.dao;


import java.util.List;

import com.npu.libraryapp.domain.Member;


public interface MemberDAO {

	public int getMemberCount();
	public String findMemberNameById(int id);
	public Member findMemberByName(String name);
	public void insertMember(Member member);
	public int deleteByMemberId(int id);
	public  List<Member> findMemberById(int memid);
	
	public boolean validate(Member member);
	
	public List<Member> findAllMembers(int id);
	
	

}
