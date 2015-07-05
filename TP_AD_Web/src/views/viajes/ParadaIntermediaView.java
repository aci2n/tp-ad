package views.viajes;

import views.GenericView;
import views.misc.UbicacionView;

public class ParadaIntermediaView extends GenericView{
	private String llegada;
	private String llegadaEsperada;
	private UbicacionView ubicacion;
	private String sucursal;
	private Integer orden;

	
	public ParadaIntermediaView(){
		
	}
	
	public ParadaIntermediaView(String llegadaEsperada, UbicacionView ubicacion) {
		this(llegadaEsperada, null, ubicacion, null);
	}
	
	public ParadaIntermediaView(String llegadaEsperada, String llegada, UbicacionView ubicacion, Integer orden) {
		this(llegadaEsperada, null, ubicacion, orden, null);
	}
	
	public ParadaIntermediaView(String llegadaEsperada, String llegada, UbicacionView ubicacion, Integer orden, String sucursal) {
		this.setLlegadaEsperada(llegadaEsperada);
		this.llegada = llegada;
		this.ubicacion = ubicacion;
		this.setSucursal(sucursal);
		this.setOrden(orden);
	}

	public String getLlegada() {
		return llegada;
	}

	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}

	public UbicacionView getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionView ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getLlegadaEsperada() {
		return llegadaEsperada;
	}

	public void setLlegadaEsperada(String llegadaEsperada) {
		this.llegadaEsperada = llegadaEsperada;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
