package jmybatis;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		DBUtil my = new DBUtil();
		my.init();

		Scanner s = new Scanner(System.in);
		UserDTO loggedInUser = null;

		while (true) {
			if (loggedInUser == null) {
				// 로그인되지 않은 경우
				System.out.println("로그인을 먼저 해주세요.");
				System.out.print("아이디 입력 : ");
				String user_id = s.nextLine();
				System.out.print("비밀번호 입력 : ");
				String user_pw = s.nextLine();

				// 로그인 시도
				loggedInUser = my.login(user_id, user_pw);

				if (loggedInUser == null) {
					System.out.println("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");

					while (true) {
						System.out.print("회원가입을 하시겠습니까? (Y/N): ");
						String choice = s.nextLine().trim();

						if (choice.equalsIgnoreCase("Y")) {
							System.out.println("회원 가입을 시작합니다.");

							System.out.print("아이디 입력 : ");
							String signup_id = s.nextLine();

							if (my.isIdExist(signup_id)) {
								System.out.println("이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
								continue;
							}

							System.out.print("비밀번호 입력 : ");
							String signup_pw = s.nextLine();

							System.out.print("비밀번호 확인 : ");
							String confirmPassword = s.nextLine();

							if (!signup_pw.equals(confirmPassword)) {
								System.out.println("비밀번호가 일치하지 않습니다. 다시 시도하세요.");
								continue;
							}

							System.out.print("이름 입력 : ");
							String name = s.nextLine();
							System.out.print("전화번호 입력 (예시 : 010-1111-1111) : ");
							String phone = s.nextLine();
							System.out.print("등급: ");
							String grade = s.nextLine();
							System.out.print("나이 : ");
							int age = Integer.parseInt(s.nextLine());

							my.insertUser(signup_id, signup_pw, name, phone, grade, age);

							System.out.println("회원가입 성공!!");
							break;
						} else if (choice.equalsIgnoreCase("N")) {
							break;
						} else {
							System.out.println("잘못된 입력입니다. 다시 시도하세요.");
						}
					}
				} else {
					System.out.println("로그인 성공! " + loggedInUser.getUser_id() + "님 환영합니다.");
				}
			} else {
				System.out.println("1. 사용자 추가");
				System.out.println("2. 사용자 조회");
				System.out.println("3. 사용자 수정");
				System.out.println("4. 사용자 삭제");
				System.out.println("5. 로그아웃");
				System.out.print("메뉴를 선택하세요: ");
				int choice = Integer.parseInt(s.nextLine());

				switch (choice) {
				case 1:
					System.out.print("아이디 입력 : ");
					String user_id = s.nextLine();

					// 중복된 아이디가 있는지 확인
					if (my.isIdExist(user_id)) {
						System.out.println("이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
						break;
					}

					System.out.print("비밀번호 입력 : ");
					String user_pw = s.nextLine();
					System.out.print("이름 입력 : ");
					String name = s.nextLine();
					System.out.print("전화번호 입력 (예시 : 010-1111-1111) : ");
					String phone = s.nextLine();
					System.out.print("등급: ");
					String grade = s.nextLine();
					System.out.print("나이 : ");
					int age = Integer.parseInt(s.nextLine());

					my.insertUser(user_id, user_pw, name, phone, grade, age);
					System.out.println("사용자가 추가되었습니다.");
					break;
				case 2:
					if (my.isEmpty()) {
						System.out.println("사용자 목록이 비어 있습니다.");
						break;
					} else {
						ArrayList<UserDTO> list = my.getUser();
						for (UserDTO user : list) {
							System.out.println(user);
						}
						break;
					}
				case 3:
					if (my.isEmpty()) {
						System.out.println("사용자 목록이 비어 있습니다.");
						break;
					} else {
						ArrayList<UserDTO> list = my.getUser();
						System.out.println(list);

						System.out.print("수정할 사용자의 아이디 입력: ");
						String updateUser_id = s.nextLine();

						// 수정할 사용자가 데이터베이스에 있는지 확인
						if (!my.isIdExist(updateUser_id)) {
							System.out.println("존재하지 않는 사용자입니다.");
							break;
						}

						System.out.print("새로운 비밀번호 입력 : ");
						String newUser_pw = s.nextLine();
						System.out.print("새로운 이름 입력 : ");
						String newName = s.nextLine();
						System.out.print("새로운 전화번호 입력 : ");
						String newPhone = s.nextLine();
						System.out.print("새로운 등급 입력 : ");
						String newGrade = s.nextLine();
						System.out.print("새로운 나이 입력 : ");
						int newAge = Integer.parseInt(s.nextLine());

						my.updateUser(updateUser_id, newUser_pw, newName, newPhone, newGrade, newAge);
						System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
						System.out.println("수정된 사용자 정보:");
						ArrayList<UserDTO> list1 = my.getUser();
						for (UserDTO user : list1) {
							System.out.println(user);
						}
						break;
					}
				case 4:
					if (my.isEmpty()) {
						System.out.println("사용자 목록이 비어 있습니다.");
						break;
					} else {
						ArrayList<UserDTO> list = my.getUser();
						System.out.println(list);

						System.out.print("삭제할 사용자의 아이디 입력: ");
						String deleteUser_id = s.nextLine();

						// 삭제할 사용자가 데이터베이스에 있는지 확인
						if (!my.isIdExist(deleteUser_id)) {
							System.out.println("존재하지 않는 사용자입니다.");
							break;
						}

						my.deleteUser(deleteUser_id);
						System.out.println("사용자가 삭제되었습니다.");
						break;
					}
				case 5:
					loggedInUser = null; // 로그인 사용자 초기화 (로그아웃)
					System.out.println("로그아웃되었습니다.");
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					break;
				}
			}
		}
	}
}