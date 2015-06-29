package impl.vehiculos;

public enum TipoVehiculo {
	TRACTOR("Tractor"),
	CAMION_CON_CAJA_REFRIGERADO("Camion con caja refrigerado"),
	CAMION_CON_CAJA("Camion con caja"),
	CAMION_CON_TANQUE("Camion con tanque"),
	CAMIONETA("Camioneta"),
	CARRIER("Carrier");
	
	private String tipo;
	
	private TipoVehiculo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
