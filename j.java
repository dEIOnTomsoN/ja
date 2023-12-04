
import java.sql.*;
class DBConnection{
	Statement stmt;	
	ResultSet rs;
	Connection con;
	DBConnection()
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			stmt=con.createStatement();
			rs=stmt.executeQuery("select id,name,deptid,salary from emp");
			while(rs.next()){
				System.out.print(rs.getInt(1)+ "   ");
				System.out.print(rs.getString(2)+ "   ");
				System.out.println(rs.getInt(3));
			}
			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}	
	public static void main(String args[])
	{
		new DBConnection();		
	}

}
