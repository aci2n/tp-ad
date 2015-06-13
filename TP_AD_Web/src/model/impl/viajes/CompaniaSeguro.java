package model.impl.viajes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.impl.PersistentObject;

@Entity
@Table(name = "Companias_Seguros")
@AttributeOverride(name = "id", column = @Column(name = "id_compania_seguros"))
public class CompaniaSeguro extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7546501742115547665L;

	
	@Column(name = "cuil")
	private String cuil;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany
	@JoinColumn(name = "id_compania_seguros")
	private List<Seguro> seguros;

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Seguro> getSeguros() {
		return seguros;
	}

	public void setSeguros(List<Seguro> seguros) {
		this.seguros = seguros;
	}
	
	public void agregarSeguro(Seguro seguro) {
		if (seguros == null)
			seguros = new ArrayList<Seguro>();
		seguros.add(seguro);
	}

}
