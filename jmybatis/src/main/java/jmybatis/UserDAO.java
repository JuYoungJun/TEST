package jmybatis;

import java.sql.*;
import java.util.*;

public class UserDAO {

	Connection conn = null;
	PreparedStatement pt = null;

	String dbDriver = "com.mysql.cj.jdbc.Driver";
	String dbUrl = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8 & serverTimexone=UTC";
	String dbID = "root";
	String dbPW = "1234";

	public void insertUser(UserDTO userDTO) {
		try {
			Class.forName(dbDriver);

			conn = DriverManager.getConnection(dbUrl, dbID, dbPW);

			String insertSQL = "insert into user values (?, ?, ?, ?, ?, ?)";

			pt = conn.prepareStatement(insertSQL);

			pt.setString(1, userDTO.getUser_id());
			pt.setString(2, userDTO.getUser_pw());
			pt.setString(3, userDTO.getName());
			pt.setString(4, userDTO.getPhone());
			pt.setString(5, userDTO.getGrade());
			pt.setInt(6, userDTO.getAge());

			pt.executeUpdate();

			pt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectUser() {
		List<UserDTO> users = new ArrayList<UserDTO>();

		try {
			Class.forName(dbDriver);

			conn = DriverManager.getConnection(dbUrl, dbID, dbPW);

			String selectSQL = "select * from user";

			pt = conn.prepareStatement(selectSQL);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO(rs.getString("user_id"), rs.getString("user_pw"), rs.getString("name"),
						rs.getString("phone"), rs.getString("grade"), rs.getInt("age"));
				users.add(dto);
			}

			for (UserDTO oneuser : users) {
				System.out.println(oneuser);
			}

			pt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateUser(UserDTO userDTO) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbID, dbPW);

			String updateSQL = "update user set user_pw=?, name=?, phone=?, grade=?, age=? where user_id=?";

			pt = conn.prepareStatement(updateSQL);

			pt.setString(1, userDTO.getUser_pw());
			pt.setString(2, userDTO.getName());
			pt.setString(3, userDTO.getPhone());
			pt.setString(4, userDTO.getGrade());
			pt.setInt(5, userDTO.getAge());
			pt.setString(6, userDTO.getUser_id());

			pt.executeUpdate();

			pt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(String userId) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbID, dbPW);

			String deleteSQL = "delete from user where user_id=?";

			pt = conn.prepareStatement(deleteSQL);
			pt.setString(1, userId);

			pt.executeUpdate();

			pt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		List<UserDTO> users = selectAllUsers();
		return users.isEmpty();
	}

	private List<UserDTO> selectAllUsers() {
		List<UserDTO> users = new ArrayList<>();

		try {
			Class.forName(dbDriver);

			conn = DriverManager.getConnection(dbUrl, dbID, dbPW);

			String selectSQL = "select * from user";

			pt = conn.prepareStatement(selectSQL);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO(rs.getString("user_id"), rs.getString("user_pw"), rs.getString("name"),
						rs.getString("phone"), rs.getString("grade"), rs.getInt("age"));
				users.add(dto);
			}

			pt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	public boolean isUserExist(String userId) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbID, dbPW);

			String selectSQL = "select * from user where user_id=?";
			pt = conn.prepareStatement(selectSQL);
			pt.setString(1, userId);
			ResultSet rs = pt.executeQuery();

			boolean exist = rs.next();

			pt.close();
			conn.close();

			return exist;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
