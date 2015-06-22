package views.clientes;

public class CuentaCorrienteView {

	private int id;
	private String depositoPrevio;
	private Float montoAutorizado;
	private Float montoActual;

	public CuentaCorrienteView() {

	}

	public CuentaCorrienteView(String depositoPrevio, Float montoAutorizado,
			Float montoActual) {

		this.depositoPrevio = depositoPrevio;
		this.montoAutorizado = montoAutorizado;
		this.montoActual = montoActual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String isDepositoPrevio() {
		return depositoPrevio;
	}

	public void setDepositoPrevio(String depositoPrevio) {
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
