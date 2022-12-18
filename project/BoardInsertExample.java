package project;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardInsertExample {
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
			String sql = "" +
				"INSERT INTO boards (btitle, bcontent, bwriter, bdate, bfilename, bfiledata) " +
				"VALUES (?, ?, ?, now(), ?, ?)";
			
			//PreparedStatement ��� �� �� ����
			PreparedStatement pstmt = conn.prepareStatement(
					sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "ũ��������");
			pstmt.setString(2, "�޸�ũ��������~.");
			pstmt.setString(3, "chrismas");
			pstmt.setString(4, "chrismas.jpg");
			pstmt.setBlob(5, new FileInputStream("src/project/chrismas.jpg"));
			
			//SQL�� ����
			int rows = pstmt.executeUpdate();
			System.out.println("����� �� ��: " + rows);
			
			//bno �� ���
			if(rows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("����� bno: " + bno);
				}
				rs.close();
			}
			
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



