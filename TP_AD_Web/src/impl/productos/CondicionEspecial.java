package impl.productos;

public enum CondicionEspecial {
	TEMPERATURA("Temperatura", 0.0025f),
	SEGURIDAD("Seguridad", 0.006f),
	MONITOREO_SATELITAL("Monitoreo satelital", 0.0095f);
	
	private String condicion;
	private Float factorCondicion;
	
	private CondicionEspecial(String condicion, Float factorCondicion) {
		this.condicion = condicion;
		this.factorCondicion = factorCondicion;
	}

	public String getCondicion() {
		return condicion;
	}

	public Float getFactorCondicion() {
		return factorCondicion;
	}
	
}
