package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDeleteExample {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//JDBC Driver ���
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//�����ϱ�
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/show_db", 
				"project", 
				"12345"
			);
			
			//�Ű�����ȭ�� SQL�� �ۼ�
			String sql = "DELETE FROM boards WHERE bwriter=?";
			
			//PreparedStatement ��� �� �� ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");
			
			//SQL�� ����
			int rows = pstmt.executeUpdate();
			System.out.println("������ �� ��: " + rows);
			
			//PreparedStatement �ݱ�
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//���� ����
					conn.close(); 
				} catch (SQLException e) {}
			}
		}
	}
}
