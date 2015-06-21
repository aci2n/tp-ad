package model.impl.clientes;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.persistence.ClienteDAO;

@Entity
@Table(name = "Clientes")
@AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178101173670139637L;
	@Column(name = "nombre")
	protected String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public abstract void cobrarEnvio(Factura factura);
}
