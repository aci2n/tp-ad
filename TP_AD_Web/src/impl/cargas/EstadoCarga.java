package impl.cargas;

public enum EstadoCarga {
	EN_DEPOSITO("En deposito"),
	EN_VIAJE("En viaje"),
	ENTREGADA("Entregada");
	
	private String estado;
	
	private EstadoCarga(String estado){
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}	
}
