package com.ssafy.ssafit.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.util.DBUtil;

public class UserRepositoryImpl implements UserRepository {
	
	private DBUtil util = DBUtil.getInstance();
	
	private static final UserRepository INSTANCE = new UserRepositoryImpl();
	private UserRepositoryImpl() {}
	public static UserRepository getInstance() {
		return INSTANCE;
	}
	
	@Override
	public User save(User user) {
		String sql = "INSERT INTO users(user_id, password, email) VALUES (?, ?, ?)";
				
		try (Connection conn = util.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()){
					if (rs.next()) {
						user.setNo(rs.getInt(1)); // users의 PK값으로 재설정
					}
				}
			} else {
				return null; // 회원 저장 실패
			}
		} catch (SQLException e) {
			System.out.println("[UserRepo] save() 예외 발생");
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public void saveUserVideos(int userNo, List<Integer> videoNos) {
		// to do: 추후 구현
	}
	
	@Override
	public User findById(String id) {
		User user = null;
		String sql = "SELECT u.no AS user_no, u.user_id, u.password, u.email, sv.video_no "
				+ "FROM users u "
				+ "LEFT JOIN saved_video sv ON u.no = sv.user_no "
				+ "WHERE u.user_id=? "
				+ "ORDER BY sv.video_no";
		
		try (Connection conn = util.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					if (user == null) {
						user = new User();
						user.setNo(rs.getInt("user_no"));
						user.setId(rs.getString("user_id"));
						user.setPassword(rs.getString("password"));
						user.setEmail(rs.getString("email"));
						user.setSavedVideoList(new ArrayList<>());
					}
					
					int videoNo = rs.getInt("video_no");
					if (!rs.wasNull()) {
						user.getSavedVideoList().add(videoNo);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("[UserRepo] findById() 예외 발생");
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public List<User> findAll() {
		Map<Integer, User> userMap = new HashMap<>();
		String sql = "SELECT u.no AS user_no, u.user_id, u.password, u.email, sv.video_no "
				+ "	FROM users u "
				+ "	LEFT JOIN saved_video sv ON u.no = sv.user_no "
				+ "	ORDER BY u.no, sv.video_no";
		
		try (Connection conn = util.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				int userNo = rs.getInt("user_no");
				User user = userMap.get(userNo);
				
				// user의 중복 생성을 막기 위함
				if (user == null) {
					user = new User();
					user.setNo(userNo);
					user.setId(rs.getString("user_id"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setSavedVideoList(new ArrayList<>());
					userMap.put(userNo, user);
				}
				// user가 저장한 video 정보 넣어주기 
				int videoNo = rs.getInt("video_no");
				if (!rs.wasNull()) {
					user.getSavedVideoList().add(videoNo);
				}
			}
		} catch (SQLException e) {
			System.out.println("[UserRepo] findAll() 예외 발생");
			e.printStackTrace();
		}
		
		return new ArrayList<>(userMap.values());
	}
	
	@Override
	public boolean update(User updateUser) {
		User foundUser = findById(updateUser.getId());
		
		if (foundUser != null) {
			foundUser.setPassword(updateUser.getPassword());
	        foundUser.setSavedVideoList(updateUser.getSavedVideoList());
			return true;
		}

		return false;
	}
	
	@Override
	public boolean delete(String id) {
		User foundUser = findById(id);
		
		if (foundUser != null) {
//			userList.remove(foundUser);
			return true;
		}
		return false;
	}

}
