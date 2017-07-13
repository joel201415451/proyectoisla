package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

import org.joda.time.DateTime;

import entidad.Transaccion;
import util.Util;

public class HiloTransaccion implements Runnable{
	Thread t;
	String nombre;
	int isla;
	int dispensador;
	int mangera;
	long valor;
	File fichero;
	//empieza main cliente
	double precioxGalon;
	 /**
	    * Puerto
	    * */
	    private final static int PORT = 5002;
	    /**10
	    * Host
	    * */
	    private final static String SERVER = "localhost";
	    
	    private final static String ATM = "00012";
	       
	    public static void main(String[] args) {
	    	
	       
	    }
	    
		
	//maincliente
	public HiloTransaccion(){
		t = new Thread(this, this.getClass().getName());
		t.start();
	}
	public HiloTransaccion(String nombre, int isla, int dispensador, int mangera, double precio,long valor){
		fichero=new File("C:\\log\\data.log");
		this.nombre = nombre;
		this.mangera=mangera;
		this.isla= isla;
		this.dispensador = dispensador;
		this.precioxGalon = precio;
		this.valor=valor;
		t = new Thread(this, this.getClass().getName());
		t.start();
	}
	
	public void run(){
		boolean exit=false;//bandera para controlar ciclo del programa
        Socket socket;//Socket para la comunicacion cliente servidor     
		try{
			for(int i=0; i<1; i++){
				//Escritura de la Transacción
				EscribirFichero(fichero, generarTransaccion());
				System.out.println(nombre + " : " + generarTransaccion() + " - " + i);
				Thread.sleep(valor); try {            
		            System.out.println("Cliente> Inicio");  
		            while( !exit ){//ciclo repetitivo                                
		                socket = new Socket(SERVER, PORT);//abre socket                
		                //Para leer lo que envie el servidor      
		                BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
		                //para imprimir datos del servidor
		                PrintStream output = new PrintStream(socket.getOutputStream()); 
		                String request = "";
		                String numTar ="";
		                String monto  = "";
		                //Para leer lo que escriba el usuario            
		                BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));           
		                //System.out.println("Cliente> Por favor ingrese su numero de tarjeta: "); 
		                //request = brRequest.readLine();
		                System.out.println("Cliente> Por favor ingrese el monto a retirar: ");                
		                //captura comando escrito por el usuario
		                request = generarTransaccion().toString();//brRequest.readLine();   //
		               
		                String nomIs="1";
		                //manda peticion al servidor
		                //output.println(generarTransaccion(numTar, monto).toString()); 
		                output.println(request); 
		                //captura respuesta e imprime
		                String st = input.readLine();
		               if( st.equals(nomIs)) 
		            	   t.stop();
		                if( st != null ) System.out.println("Servidor> " + st );    
		                if(request.equals("exit")){//terminar aplicacion
		                    exit=true;                  
		                    System.out.println("Cliente> Fin de programa");    
		                }  
		                socket.close();
		            }//end while                                    
		       } catch (IOException ex) {        
		         System.err.println("Cliente> " + ex.getMessage());   
		       }
			}
		}catch(InterruptedException e){
			
		}
	}
	
	private Transaccion generarTransaccion(){
		Transaccion objeto = new Transaccion();
		double cantGalon = Util.getAleatorioEntero(1, 100);
		double totalVenta = Util.redondearDecimales(precioxGalon*cantGalon,2);
		DateTime fecha = new DateTime();
		objeto.setNombre(nombre+ " : " );
		objeto.setFechaTransaccion(fecha.toString("ddMMyyyy"));
		objeto.setHoraTransaccion(fecha.toString("hhmmss"));
		//objeto.setCodigoDispensador(String.format("%02d", dispensador));
		//objeto.setCodigoMangera(String.format("%02d", mangera));
		objeto.setCantidadGalones(String.format("%010d",Util.getAleatorioEntero(1, 100)));
		objeto.setPrecioVenta(String.format("%010d",Util.getAleatorioEntero(10, 12)));
		//objeto.setTotalVenta(String.format("%010d", (int) (totalVenta*100d)));
		
		return objeto;
	}
	
	public static void EscribirFichero(File fichero, Transaccion SCadena){
		  try {
		           if(!fichero.exists()){
		               fichero.createNewFile();
		           }
		          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero,true), "utf-8"));
		          Fescribe.write(SCadena + "\r\n");
		          Fescribe.close();
		   } catch (Exception ex) {
		          System.out.println(ex.getMessage());
		   } 
	}
	

}
