package model.impl.clientes;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.misc.Ubicacion;
import model.persistence.ReceptorDAO;
import model.views.UbicacionView;

@Entity
@Table(name = "Receptores")
@AttributeOverride(name = "id", column = @Column(name = "id_receptor"))
public class Receptor extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143126364253623009L;
	@Column(name = "dni")
	private String dni;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@ManyToOne
	@JoinColumn(name = "id_ubicacion")
	private Ubicacion ubicacion;

	public Receptor(String dni, String nombre, String apellido, UbicacionView u) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.ubicacion = new Ubicacion(u.getPais(), u.getProvincia(), u.getCiudad(), u.getCalle(), u.getAltura(), u.getPiso(), u.getDepartamento(),
				u.getCoordenadaDestino());
		this.id = ReceptorDAO.getInstance().insert(this);
	}

	public Receptor() {
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
}
