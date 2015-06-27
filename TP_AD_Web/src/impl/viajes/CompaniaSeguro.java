package impl.viajes;

import impl.PersistentObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import persistence.CompaniaSeguroDAO;
import views.viajes.CompaniaSeguroView;
import views.viajes.SeguroView;

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

	public CompaniaSeguro(CompaniaSeguroView c) {
		cuil = c.getCuil();
		nombre = c.getNombre();
		id = CompaniaSeguroDAO.getInstance().insert(this);
	}

	public CompaniaSeguro() {

	}

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

	public Integer agregarSeguro(SeguroView s) {
		if (seguros == null)
			seguros = new ArrayList<Seguro>();
		Seguro seguro = new Seguro(s);
		seguros.add(seguro);
		CompaniaSeguroDAO.getInstance().update(this);
		return seguro.getId();
	}

	public CompaniaSeguroView getView() {
		CompaniaSeguroView compania = new CompaniaSeguroView(cuil, nombre);
		Collection<SeguroView> segurosV = new ArrayList<SeguroView>();
		for (Seguro s : seguros) {
			segurosV.add(s.getView());
		}
		compania.setSegurosView(segurosV);
		return compania;
	}

}
