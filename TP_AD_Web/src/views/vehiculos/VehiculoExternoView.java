package views.vehiculos;

import views.GenericView;
import views.misc.TamanoView;

public class VehiculoExternoView extends GenericView{
	private String patente;
	private TamanoView tamano;
	private Float peso;
	private Float tara;
	private Float tarifa;
	private String tipo;

	public VehiculoExternoView(String patente, TamanoView t, float peso, float tara, float tarifa, String tipo) {
		this.patente = patente;
		this.tamano = t;
		this.peso = peso;
		this.tara = tara;
		this.tarifa = tarifa;
		this.tipo = tipo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public TamanoView getTamano() {
		return tamano;
	}

	public void setTamano(TamanoView tamano) {
		this.tamano = tamano;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getTara() {
		return tara;
	}

	public void setTara(Float tara) {
		this.tara = tara;
	}

	public Float getTarifa() {
		return tarifa;
	}

	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
