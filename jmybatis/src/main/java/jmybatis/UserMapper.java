package jmybatis;

import java.util.*;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	// 리턴타입, 메서드명, 매개변수

	public ArrayList<UserDTO> getUser();

	public void insertUser(UserDTO userDTO);

	public void updateUser(UserDTO userDTO);

	public void deleteUser(@Param("user_id") String user_id);

	public int isEmpty(); // 비어있는지 확인용

	public boolean isIdExist(@Param("user_id") String user_id); // 아디중복확인

	UserDTO getUserByIdAndPassword(@Param("user_id") String user_id, @Param("user_pw") String user_pw);
}
