	package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			
			//faz com que os cófigos fiquem aguardando o commit para de fato serem executados
			conn.setAutoCommit(false);
			
			int rows1 = st.executeUpdate("UPDATE seller "
					+ "SET BaseSalary = 2090 "
					+ "WHERE DepartmentId = 1");
			
			int rows2 = st.executeUpdate("UPDATE seller "
					+ "SET BaseSalary = 3090 "
					+ "WHERE DepartmentId = 2");
			
			//executa de fato os comandos
			conn.commit();
			System.out.println("Done! Rows affected 1: "+rows1);
			System.out.println("Done! Rows affected 2: "+rows2);

		}catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: "+e.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new DbException("Error trying to rollback! Caused by: "+e1.getMessage());
			}
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
