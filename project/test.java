package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
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
			
			System.out.println("���� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//���� ����
					conn.close(); 
					System.out.println("���� ����");
				} catch (SQLException e) {}
			}
		}
	}
}
