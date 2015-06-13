package model.impl.clientes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Clientes_Particulares")
@AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
public class Particular extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516360528659218982L;

	@OneToMany
	@JoinColumn(name = "id_cliente")
	private List<Receptor> receptores;
	@Column(name = "dni")
	private String dni;
	@Column(name = "apellido")
	private String apellido;

	public Particular(String codigoUnico, String dni, String nombre,
			String apellido) {
		this.codigoUnico = codigoUnico;
		this.dni = dni;
		this.apellido = apellido;

	}

	public Particular() {
		
	}

	public List<Receptor> getReceptores() {
		return receptores;
	}

	public void setReceptores(List<Receptor> receptores) {
		this.receptores = receptores;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public void cobrarEnvio(Factura factura) {
		// TODO Auto-generated method stub
		
	}
	
	public void agregarReceptor(Receptor receptor) {
		if (receptores == null)
			receptores = new ArrayList<Receptor>();
		receptores.add(receptor);
	}

}
