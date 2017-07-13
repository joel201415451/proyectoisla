package util;

import java.util.Random;

public class Util {

	public static int getAleatorioEntero(int inicio, int fin){
		Random rnd = new Random();
		int v = 0;
		v = (int) (rnd.nextDouble() * fin + inicio);
		return v;
	}
	
	public static double getAleatorioDecimal(int CantDecimal){
		Random rnd = new Random();
		double v = 0d;
		v = rnd.nextDouble();
		return redondearDecimales(v,CantDecimal);
	}
	
    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
}
