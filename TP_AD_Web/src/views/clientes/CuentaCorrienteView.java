package views.clientes;

import views.GenericView;

public class CuentaCorrienteView extends GenericView{

	private boolean depositoPrevio;
	private Float montoAutorizado;
	private Float montoActual;

	public CuentaCorrienteView() {

	}

	public CuentaCorrienteView(boolean depositoPrevio, Float montoAutorizado, Float montoActual) {
		this.depositoPrevio = depositoPrevio;
		this.montoAutorizado = montoAutorizado;
		this.montoActual = montoActual;
	}

	public boolean isDepositoPrevio() {
		return depositoPrevio;
	}

	public void setDepositoPrevio(boolean depositoPrevio) {
		this.depositoPrevio = depositoPrevio;
	}

	public Float getMontoAutorizado() {
		return montoAutorizado;
	}

	public void setMontoAutorizado(Float montoAutorizado) {
		this.montoAutorizado = montoAutorizado;
	}

	public Float getMontoActual() {
		return montoActual;
	}

	public void setMontoActual(Float montoActual) {
		this.montoActual = montoActual;
	}

}
