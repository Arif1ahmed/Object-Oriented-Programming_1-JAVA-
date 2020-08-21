import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database{
	Connection conn;
	String DB_URL;
	String JDBC_DRIVER;
	Statement stmt;
	String USER ;
	String PASS ;
	ResultSet rs;
	
	public Database(){
		JDBC_DRIVER = "com.mysql.jdbc.Driver";
		DB_URL = "jdbc:mysql://localhost:3306/customerdb";
		USER = "root";
		PASS = "";
		
		try{
			
			Class.forName(JDBC_DRIVER);
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void close()throws SQLException{
		if(rs!=null)rs.close();
		if(rs!=null)stmt.close();
	}
	public ResultSet getData(String query){
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return rs;
	}
	public int updateDB(String sql){
		int numOfRowsUpdated=0;
		try{
			stmt = conn.createStatement();
			numOfRowsUpdated=stmt.executeUpdate(sql);
			System.out.println(numOfRowsUpdated+" row(s) updated");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return numOfRowsUpdated;
	}

}