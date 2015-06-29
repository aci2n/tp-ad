package impl.cargas;

public enum TipoCarga {
	PAQUETE("Paquete"),
	CAJA("Caja"),
	PALLET("Pallet"),
	BOLSA("Bolsa"),
	BIDON("Bidon"),
	TAMBOR("Tambor"),
	BARRIL("Barril"),
	GRANEL("Granel");
	
	private String tipo;
	
	private TipoCarga(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public static TipoCarga obtenerPorTipo(String tipo) {
		for (TipoCarga tipoCarga : values()) {
			if (tipoCarga.tipo.equalsIgnoreCase(tipo))
				return tipoCarga;
		}
		return null;
	}
}
