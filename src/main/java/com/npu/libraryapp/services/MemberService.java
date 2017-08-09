package com.npu.libraryapp.services;

import com.npu.libraryapp.domain.Member;



public interface MemberService {
	public void addNewMember(Member member);
	public void deleteByMemberId(int memid);
	public int getTotalNumberOfMembers();
	public Member findMemberDetails(String name);
	public String findMemberNameById(int memid);
	
	public boolean validate(Member loginModel);
	
}
