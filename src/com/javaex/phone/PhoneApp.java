package com.javaex.phone;

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
				System.out.println("*	ㄳㄳㄳㄳㄳㄳㄳㄳ		*");
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
				
				List<PhoneVo> searchList = phoneDao.personSearch(search);
				
			default:
				System.out.println("[다시 입력해 주세요]");		
			}
			
		}
		
		/*
		//프로그램 시작 문구 출력
		Person.mainView();

		
		while (true) {
			}

			switch (select) {
			case 1:
				System.out.println("<1.리스트>");
				for (int i = 0; i < pList.size(); i++) {
					System.out.println(i + 1 + ".	" + pList.get(i).getName() + "	" + pList.get(i).getHp() + "	"
							+ pList.get(i).getCompany());
				}
				break;
				
			//등록해야되니까 새로 메모리에 올려주고 추가해.
			case 2:
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = sc.next();
				System.out.print(">휴대전화: ");
				String hp = sc.next();
				System.out.print(">회사전화: ");
				String company = sc.next();
				System.out.println("[등록되었습니다.]");

				Person p = new Person(name, hp, company);
				pList.add(p);
				break;
				
			//지우는데 그냥 숫자쓰면 0부터 시작이니까 1지울거면 -1붙여라. 그냥 하면 0번방은 남는다.	
			case 3:
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				int re = sc.nextInt();
				System.out.println("[삭제되었습니다.]");
				// -1해야함. 숫자1 썻을때 그냥 1번방 지우는게 되므로 -1해야 0번부터 지울 수 있음.ㅇㅋ?
				pList.remove(re - 1);

				break;
				
			//contains는 문자열 안에 조건에 맞는거 찾아준다.
			//검색한 후에 검색한거 출력해야 하니까 어떤걸 검색할건지 고민하고 여기선 이름 검색이니까 name해줘라.
			case 4:
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String del = sc.next();

				for (int i = 0; i < pList.size(); i++) {

					if (pList.get(i).getName().contains(del)) {
						System.out.println(i + 1 + ".	" + pList.get(i).getName() + "	" + pList.get(i).getHp()
								+ "	" + pList.get(i).getCompany());
					}
				}

				break;
				

		
*/
	
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
