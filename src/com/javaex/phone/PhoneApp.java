package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {


		//스캐너값 받아옴.
		Scanner sc = new Scanner(System.in);
				
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList;
		
		System.out.println("*************************************************");
		System.out.println("*           전화번호 관리 프로그램              *");
		System.out.println("*************************************************");
		
		
		
		//읽는거 반복 돌리고
		while (true) {

			PhoneDao.Menu();		

			int num = sc.nextInt();
			
			if (num == 6) {
				System.out.println("*********************************");
				System.out.println("*	ㄳㄳㄳㄳㄳㄳㄳㄳ	*");
				System.out.println("*********************************");
				break;
			}
			
			String name = "";
			String hp = "";
			String company = "";
			int select = 0;
			int personId = 0;
			
			
			switch (num) {
			
			case 1:
				System.out.println("<1.리스트>");
				personList = phoneDao.getPersonList();
				pritnList(personList);
				break;
	
			case 2:
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				name = sc.nextLine();
				System.out.print(">휴대전화: ");
				hp = sc.nextLine();
				System.out.print(">회사전화: ");
				company = sc.nextLine();
				System.out.println("[등록되었습니다.]");

				PersonVo p = new PersonVo(name, hp, company);
				phoneDao.personInsert(p);
				break;	
				
			case 3:	
				System.out.println("<3.수정>");
				System.out.print("번호 > ");
				personId = sc.nextInt();
				System.out.print("이름 > ");
				name = sc.next();
				System.out.print("휴대전화> ");
				hp = sc.next();
				System.out.print("회사번호> ");
				company = sc.next();
				System.out.println("[등록되었습니다.]");
								
				
				PersonVo pUpdate = new PersonVo(personId, name, hp, company);
				phoneDao.personUpdate(pUpdate);
				break;	
				
				
			case 4:
				System.out.println("<4.삭제>");	
				System.out.print("번호 > ");
				personId = sc.nextInt();
				
				PersonVo pDelete = new PersonVo(personId);
				phoneDao.personDelete(personId);
				break;
				
				
			case 5:
				System.out.print("검색어>   ");
				String search =sc.next();
				
				List<PersonVo> searchList = new ArrayList<PersonVo>();
				
				searchList = phoneDao.personSearch(search);
				
				pritnList(searchList);
				
				break;
				
				
			default:
				System.out.println("[다시 입력해 주세요]");		
			}
			
		}
		
		
	
	}
	
	//리스트 출력
	public static void pritnList(List<PersonVo> PersonList) {
		for (int i = 0; i < PersonList.size(); i++) {
			PersonVo personVo = PersonList.get(i);
			System.out.println(personVo.getPersonId() + "\t" + personVo.getName() + "\t" + personVo.getHp() + "\t"
					+ personVo.getCompany());
		}

	}
	


}
