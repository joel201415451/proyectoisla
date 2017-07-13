import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class interfaz extends JFrame {

	private JPanel contentPane;
	 private final static int PORT = 5002;
	    static File fichero =new File("C:\\log\\data.log");
	    
	    static String Disla1="";
	    static String Disla2;
	    
//	   boolean flag =false;
	    static boolean dato=false;
	    static JTextArea txtArea = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz frame = new interfaz();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 431);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 178, 544, 189);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(txtArea);
		
		JPanel panel = new JPanel();
		//panel.SOMEBITS(new ImageIcon(interfaz.class.getResource("/imagenes/AdminLogo.png")));
		panel.setBounds(51, 21, 555, 146);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDetenerIsla = new JButton("detener isla  1");
		btnDetenerIsla.setBounds(21, 29, 162, 23);
		panel.add(btnDetenerIsla);
		
		JButton btnCuadreIsla1 = new JButton("Cuadre Isla 1");
		btnCuadreIsla1.setBounds(21, 73, 162, 23);
		panel.add(btnCuadreIsla1);
		
		JButton btnDetenerIsla2 = new JButton("Detener Isla 2");
		btnDetenerIsla2.setBounds(308, 29, 149, 23);
		panel.add(btnDetenerIsla2);
		
		JButton btnCuadreIsla2 = new JButton("Cuadre Isla 2");
		btnCuadreIsla2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cuadre.CuadreIsla("C:\\log\\data.log","Isla 2");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCuadreIsla2.setBounds(308, 73, 149, 23);
		panel.add(btnCuadreIsla2);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cuadre.cargar("C:\\log\\data.log");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btnProcesar.setBounds(190, 112, 128, 23);
		panel.add(btnProcesar);
		btnCuadreIsla1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	cuadre.CuadreIsla("C:\\log\\data.log","Isla 1");
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
				
			}
		});
		btnDetenerIsla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnDetenerIsla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Disla1="1";
			}
		});
		//servicio(txtArea);
		Iniciar i = new Iniciar();
		
		
	}
	public class Iniciar implements Runnable{
		public Iniciar(){
			Thread	t = new Thread(this, this.getClass().getName());
		t.start();}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			servicio();
		}
		
	}
	
	public static void EscribirFichero(File fichero, String request){
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
  public static String process(String request){
    
      String monto = null;
      if(request!=null) {
      	
      monto =  request;

      	request = String.valueOf("Exitosa " +monto);
   
}
      return request;
  }
  
  
  private  void servicio(){
	  try {
          //Socket de servidor para esperar peticiones de la red
          ServerSocket serverSocket = new ServerSocket(PORT);
        
          txtArea.setText("Servidor> En espera de cliente...");    
          //Socket de cliente
          Socket clientSocket;
          while(true){
              //en espera de conexion, si existe la acepta
              clientSocket = serverSocket.accept();
              //Para leer lo que envie el cliente
              BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
              //para imprimir datos de salida                
              PrintStream output = new PrintStream(clientSocket.getOutputStream());
              //se lee peticion del cliente
              String request = input.readLine();
              System.out.println("Cliente> petición [" + request +  "]");
              //se procesa la peticion y se espera resultado
              EscribirFichero(fichero, request);
              String strOutput;
              //Se imprime en consola "servidor"
              System.out.println("Servidor> Resultado de petición");                    
              if (Disla1.equals("1")){
            	  request = String.valueOf("1");
            	strOutput=request; }
                        
             // System.out.println("Servidor> \"" + strOutput + "\"");}
              else{
              	strOutput = process(request);
              System.out.println("Servidor> \"" + strOutput + "\"");}
              
              //se imprime en cliente
              output.flush();//vacia contenido
              output.println(strOutput);   
             //output.close();
              //cierra conexion
              clientSocket.close();
          }    
      } catch (IOException ex) {
          System.err.println(ex.getMessage());
      }
		
  }
  public static  boolean flag(){
		 
	  if (dato==true)
		    return true;
	  else
		  return false;
	  
  }
}
