

	import java.sql.*;

	public class conexion {
		
		public static Connection Conectar() throws Exception{
			Connection cn = null;
			try{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"+
							"databaseName=BDGrifo", "sa", "123456");
			}catch(Exception e){
				throw e;
			}
			return cn;
		}
	}

