package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자

	// 메소드g/s

	// 메소드 - 일반

	// DB연결
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원 정리
	private void close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	
	// 전화번호 리스트 가져오기
	public List<PersonVo> getPersonList() {
		// 리스트 생성
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		this.getConnection();
		
		
		try {

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  person_id, ";
			query += "		   name, ";
			query += "		   hp,";
			query += "		   company";
			query += "		   from person";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
		    // 4.결과처리
			
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				
				personList.add(personVo);
			}
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}

		this.close();
		
		return personList;
	}
	
	
	//사람 등록하기
	public int personInsert(PersonVo personVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다."); // 등록할땐 개발자가 보는 용도로만.

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return count; // 성공갯수 리턴
	}
	

	//사람 수정하기
	public int personUpdate(PersonVo personVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set    name = ?, ";
			query += " 	      hp = ?, ";
			query += "        company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return count;
	}
	
	

	
	//사람 삭제하기
	public int personDelete(int personId) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5. 자원정리
		this.close();

		return count;
	}
	
	
	
	//사람 검색하기
	public List<PersonVo> personSearch(String search) {
		
		List<PersonVo> searchList = new ArrayList<PersonVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  person_id, ";
			query += " 		   name, ";
			query += "  	   hp, ";
			query += " 		   company ";
			query += " from person ";
			query += " where (name || hp || company) like '%" + search + "%' ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				
				searchList.add(personVo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5. 자원정리
		this.close();

		return searchList;
	}
	
	
	
	//메뉴출력
	public static void Menu() {
		System.out.println("");
		System.out.println("1.리스트" + "	" + "2.등록" + "	" + "3.수정" + "	" + "4.삭제" + "	" + "5.검색" + "	" + "6.종료");
		System.out.println("-----------------------------");
		System.out.print(">메뉴선택: ");
	}
}
