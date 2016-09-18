package com.zhuoxun365.service;

import java.util.Date;
import java.util.List;

import com.huangyunchi.entity.Member;
import com.huangyunchi.entity.common.Page;
import com.huangyunchi.service.MemberService;

public class MemberServiceTest {

	
	
	public static void main(String[] args) {
		//testSave();
		
		//testFindOne();
		
		//testFindAll();
		
		//testFindByPage();
		
		//testUpdate();
		
		//testDelete();
		
		testFindByMobile();
	}
	
	
	public static void testDelete(){
		MemberService service = new MemberService();
		
		service.delete(2);
	}
	
	public static void testUpdate(){
		MemberService service = new MemberService();
		
		Member mbr = service.findOne(Integer.valueOf(1));
		mbr.setMobile("0000");
		
		service.update(mbr);
	}
	
	public static void testFindByPage(){
		MemberService service = new MemberService();
		
		Page<Member> page = service.findByPager(1, 10);
		
		System.out.println(page);
	}
	
	public static void testFindAll(){
		MemberService service = new MemberService();
		
		List<Member> list = service.findAll();
		
		for (Member member : list) {
			System.out.println(member);
		}
	}
	
	public static void testFindByMobile(){
		MemberService service = new MemberService();
		
		Member mbr = service.findByMobile("11111");
		
		System.out.println(mbr);
	}
	
	public static void testFindOne(){
		MemberService service = new MemberService();
		
		Member mbr = service.findOne(Integer.valueOf(1));
		
		System.out.println(mbr);
	}
	
	public static void testSave(){
		Member mbr = new Member();
		mbr.setMobile("119");
		mbr.setPwd("119");
		mbr.setReal_name("火哥");
		mbr.setNick_name("火火火");
		mbr.setGender(true);
		mbr.setEmail("119@119.com");
		mbr.setRegister_time(new Date());
		
		
		MemberService service = new MemberService();
		Member newMbr = service.save(mbr);
		
		System.out.println(newMbr);
	}
}
