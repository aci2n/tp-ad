package impl.clientes;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import views.clientes.CuentaCorrienteView;

@Embeddable
public class CuentaCorriente {

	@Column(name = "deposito_previo")
	private boolean depositoPrevio;
	@Column(name = "monto_autorizado")
	private Float montoAutorizado;
	@Column(name = "monto_actual")
	private Float montoActual;

	public CuentaCorriente(boolean depositoPrevio, Float montoActual, Float montoAutorizado) {
		this.depositoPrevio = depositoPrevio;
		this.montoActual = montoActual;
		this.montoAutorizado = montoAutorizado;
	}

	public CuentaCorriente() {
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

	public boolean estaAutorizado(Float monto) {
		return (montoActual + monto) <= montoAutorizado;
	}

	public void actualizarMontoActual(Float monto) throws Exception {
		if (estaAutorizado(monto)) {
			this.montoActual += monto;
		} else {
			throw new Exception("Monto no autorizado.");
		}
	}
	
	public CuentaCorrienteView getView(){
		return new CuentaCorrienteView(depositoPrevio, montoAutorizado, montoActual);
	}
}
