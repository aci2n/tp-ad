package impl.productos;

public enum TipoFragilidad {
	EXTREMADAMENTE_FRAGIL("Extremadamente fragil", 0.01f),
	FRAGIL("Fragil", 0.005f),
	NORMAL("Normal", 0f),
	RESISTENTE("Resistente", 0f);
	
	private String tipo;
	private Float factorFragilidad;
	
	private TipoFragilidad(String tipo, Float factorFragilidad) {
		this.tipo = tipo;
		this.factorFragilidad = factorFragilidad;
	}

	public String getTipo() {
		return tipo;
	}

	public Float getFactorFragilidad() {
		return factorFragilidad;
	}	
	
}