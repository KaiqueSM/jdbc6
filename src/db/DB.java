package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	
	public static Connection conn = null;
	
	
	//m�todo para iniciar conex�o ou retorn�-la caso estja iniciada	
	public static Connection getConnection() {
		if (conn==null) {
			try{
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//m�todo para fechar conex�o
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	
	//m�todo para carregar os dados do arquivo propertie
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	//m�todo auxiliar para fechatr statement
	public static void closeStatement(Statement st) {
		try {
			st.close();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	//m�todo auxiliar para fechatr resultset
	public static void closeStatement(ResultSet rs) {
		try {
			rs.close();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
