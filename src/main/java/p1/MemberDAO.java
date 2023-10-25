package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/spring4fs";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "spring4", "Spring4!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<MemberVO> listMembers() {
		connect();
		ArrayList<MemberVO> list = new ArrayList<>();
		String sql = "select * from member order by id";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getInt("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegdate(rs.getString("regdate"));
				member.setEmail(rs.getString("email"));
				list.add(member);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			disconnect();
		}
		return list;
	}

	public MemberVO getMemberById(int id) {
		connect();
		String sql = "select * from member where id=?";
		MemberVO member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getInt("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("namne"));
				member.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;

		} finally {
			disconnect();
		}

		return member;
	}
	public MemberVO getMemberByEmail(String email) {
		connect();
		String sql = "select * from member where email=?";
		MemberVO member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getInt("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("namne"));
				member.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;

		} finally {
			disconnect();
		}

		return member;
	}
}