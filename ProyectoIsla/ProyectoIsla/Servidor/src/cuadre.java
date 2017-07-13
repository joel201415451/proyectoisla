
	import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
	import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

	public class cuadre {
		 static File ficheroIsla1 =new File("C:\\log\\dataIsla1.log");
		 static File ficheroIsla2 =new File("C:\\log\\dataIsla2.log");
	    public static void cargar(String archivo) throws FileNotFoundException, IOException {
	        String cadena;
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	       
	        while((cadena = b.readLine())!=null) {
	           	try {
	        		
		        	String nombre =cadena.substring(0, 8).toString();
		        	String nombre1 =cadena.substring(0, 7).toString();
		        	String fecha=cadena.substring(9, 23).toString();
		        	double precio=Double.parseDouble(cadena.substring(29, 33).toString());
		        	int cant=Integer.parseInt(cadena.substring(39, 43).toString());
				if(nombre.equals("Isla 1 :")){
						cargar(nombre1,fecha,precio,cant);
			//	EscribirFicheroIsla(ficheroIsla1, cadena);		
				}
				else if (nombre.equals("Isla 2 :")){
					cargar(nombre1,fecha,precio,cant);
		//	EscribirFicheroIsla(ficheroIsla2, cadena);		
			}
				else{
					//System.out.println(cadena);
				}
	        	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        b.close();
	    }
	    public static void CuadreIsla(String archivo,String Isla) throws FileNotFoundException, IOException {
	        String cadena;
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	       
	        while((cadena = b.readLine())!=null) {
	           	try {
	        		
		        	String nombre =cadena.substring(0, 8).toString();
		switch (Isla) {
		case "Isla 1":
			if (nombre.equals("Isla 1 :")){
			EscribirFicheroIsla(ficheroIsla1, cadena);		
			interfaz.txtArea.append(cadena+ "\r\n");}
			break;

		case "Isla 2":
			if (nombre.equals("Isla 2 :")){
				  EscribirFicheroIsla(ficheroIsla2, cadena);		
		          interfaz.txtArea.append(cadena+ "\r\n");}
			break;
			default :
				break;
		}
		        	/*if(nombre.equals("Isla 1 :")){
						EscribirFicheroIsla(ficheroIsla1, cadena);		
						interfaz.txtArea.append(cadena);
				}
				else if (nombre.equals("Isla 2 :")){
					EscribirFicheroIsla(ficheroIsla2, cadena);		
					interfaz.txtArea.append(cadena);
			}
				else{
					System.out.println(cadena);
				}
	        	*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        b.close();
	    }
	    public static void main(String[] args) throws IOException, Exception {
	        //muestraContenido("C:\\log\\data.log");
	        
	        //prueba();
	    }
		
		public static void cargar(String nombre ,String fecha,double precio,int cantidad) throws Exception{
			try {
				
			Connection cn = conexion.Conectar();
			String sql="INSERT INTO Cliente(nombre, fechaTransaccion, precio, cantidad)"+
				"VALUES(?,? ,?,?)";
		    PreparedStatement ps = cn.prepareStatement(sql);
			int x =1;
			ps.setString(x++,nombre);
			ps.setString(x++,fecha);
			ps.setDouble(x++,precio);
			ps.setInt(x++,cantidad);
			ps.executeUpdate();
		
		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
	}
		public static void EscribirFicheroIsla(File fichero, String request){
			  try {
			           if(!fichero.exists()){
			               fichero.createNewFile();
			           }
			          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero,true), "utf-8"));
			          Fescribe.write(request + "\r\n");
			          Fescribe.close();
			   } catch (Exception ex) {
			          System.out.println(ex.getMessage());
			   } 
		}
		
	}
