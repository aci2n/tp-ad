package model.view.clientes;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.impl.misc.Ubicacion;
import model.views.UbicacionView;

public class ReceptorView {

	private int id;
	private String dni;
	private String nombre;
	private String apellido;
	private UbicacionView ubicacion;

	public ReceptorView(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UbicacionView getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionView ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}
