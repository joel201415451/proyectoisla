

import java.io.Serializable;

public class Transaccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoATM;
	private String fechaTransaccion;
	private String horaTransaccion;
	private String numeroTarjeta;
	private String monto;
	private String indicadorVenta;
	
	public String getCodigoATM() {
		return codigoATM;
	}
	public void setCodigoATM(String codigoATM) {
		this.codigoATM = codigoATM;
	}
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public String getHoraTransaccion() {
		return horaTransaccion;
	}
	public void setHoraTransaccion(String horaTransaccion) {
		this.horaTransaccion = horaTransaccion;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getIndicadorVenta() {
		return indicadorVenta;
	}
	public void setIndicadorVenta(String indicadorVenta) {
		this.indicadorVenta = indicadorVenta;
	}
	
	@Override
	public String toString() {
		return    codigoATM + fechaTransaccion + horaTransaccion
				+ numeroTarjeta + monto 
				+ indicadorVenta ;
	}
}
