
import java.sql.*;

	public class sqlC {
		
		public static int CargaTrama(entCliente u) throws Exception{

			Connection cn = conexion.Conectar();
			try{
				String sql = 
				"INSERT INTO Cliente(nombre, codisla, precio, cantidad)"+
				"VALUES(?, ?, ?, ?)";
						
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, u.getNombre());
				pst.setInt(2, u.getCodisla());
				pst.setInt(3, u.getPrecio());
				pst.setInt(4, u.getCantidad());
				int i = pst.executeUpdate();
				return i;			
			}catch(Exception e){throw e;}
			finally{cn.close();}
		}
		
		
	}

