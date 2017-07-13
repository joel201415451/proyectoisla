package entidad;

public class Transaccion {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
String nombre;
	public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}


	String fechaTransaccion;
	String horaTransaccion;
	String cantidadGalones;//'
	String precioVenta;//'
	

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

	public String getCantidadGalones() {
		return cantidadGalones;
	}
	public void setCantidadGalones(String cantidadGalones) {
		this.cantidadGalones = cantidadGalones;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	
	@Override
	public String toString() {
		return nombre+fechaTransaccion  + horaTransaccion
				+  cantidadGalones +  precioVenta;
	}
}
