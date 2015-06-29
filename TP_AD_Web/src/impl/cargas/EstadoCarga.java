package impl.cargas;

public enum EstadoCarga {
	EN_DEPOSITO("En deposito"),
	EN_VIAJE("En viaje"),
	ENTREGADA("Entregada"),
	ROTO("Roto");
	
	private String estado;
	
	private EstadoCarga(String estado){
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}	
	
	public static EstadoCarga obtenerPorEstado(String estado) {
		for (EstadoCarga e : values()) {
			if (e.estado.equalsIgnoreCase(estado))
				return e;
		}
		return null;
	}
}
