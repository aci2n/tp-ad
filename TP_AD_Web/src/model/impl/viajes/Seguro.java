package model.impl.viajes;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.TipoCarga;

@Entity
@Table(name = "Seguros")
@AttributeOverride(name = "id", column = @Column(name = "id_seguro"))
public class Seguro extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1508681287329096593L;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "tipo_carga")
	@Enumerated(value = EnumType.STRING)
	private TipoCarga tipoCarga;
	@Column(name = "tarifa")
	private Float tarifa;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoCarga getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(TipoCarga tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	public Float getTarifa() {
		return tarifa;
	}
	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}
	
}
