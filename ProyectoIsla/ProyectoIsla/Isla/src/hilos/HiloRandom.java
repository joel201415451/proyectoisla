package hilos;

import java.util.Random;

public class HiloRandom {
	public static void main(String agr[]){
		Random rmd = new Random();
		for(int i=0; i<20; i++){
			int dato = (int)(rmd.nextDouble()*5+1);
			System.out.println("valor: " +dato );
		}
	}
}
