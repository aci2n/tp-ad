package model.impl.personal;

public enum TipoPuesto {
	GERENTE("Gerente"),
	DEPOSITO("Deposito"),
	DESPACHO("Despacho"),
	RECEPCION("Recepcion"),
	OTRO("Otro");
	
	private String puesto;
	
	private TipoPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getPuesto() {
		return puesto;
	}
}
