package model.impl.vehiculos;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.persistence.ProveedorDAO;

@Entity
@Table(name = "Proveedores")
@AttributeOverride (name = "id", column = @Column(name ="id_proveedor"))
public class Proveedor extends PersistentObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4550038024972428465L;

	@Column(name = "cuit")
	private String cuit;
	@Column(name = "nombre")
	private String nombre;
	
	public Proveedor(){
		
	}
	
	public Proveedor(String cuit, String nombre){		
		this.cuit = cuit;
		this.nombre = nombre;
		this.id = ProveedorDAO.getInstance().insert(this);
	}
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public boolean existeVehiculo(String patente) {
//		for (VehiculoExterno v : vehiculos){
//			if (v.getPatente().equals(patente)){
//				return true;
//			}
//		}
//		return false;
//	}	
//	
//	public VehiculoExterno obtenerVehiculo(String patente) {
//		for (VehiculoExterno vehiculo : vehiculos) {
//			if (vehiculo.getPatente().equals(patente)) {
//				return vehiculo;
//			}
//		}
//		return null;
//	}	
//	
//	public void agregarVehiculo(VehiculoExterno vehiculo) {
//		vehiculos.add(vehiculo);		
//	}
	
}
