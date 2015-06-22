package model.views.productos;

import java.util.List;

import model.impl.productos.CondicionEspecial;
import model.impl.productos.TipoFragilidad;
import model.impl.productos.TipoTratamiento;
import model.views.misc.TamanoView;

public class ProductoView {
	private String nombre;
	private TipoFragilidad fragilidad;
	private TipoTratamiento tratamiento;
	private TamanoView tamano;
	private Float peso;
	private Integer apilable;
	private String manipulacion;
	private String material;
	private String consideraciones;
	private List<CondicionEspecial> condicionesEspeciales;
	private boolean refrigerada;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoFragilidad getFragilidad() {
		return fragilidad;
	}
	public void setFragilidad(TipoFragilidad fragilidad) {
		this.fragilidad = fragilidad;
	}
	public TipoTratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(TipoTratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public TamanoView getTamano() {
		return tamano;
	}
	public void setTamano(TamanoView tamano) {
		this.tamano = tamano;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Integer getApilable() {
		return apilable;
	}
	public void setApilable(Integer apilable) {
		this.apilable = apilable;
	}
	public String getManipulacion() {
		return manipulacion;
	}
	public void setManipulacion(String manipulacion) {
		this.manipulacion = manipulacion;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getConsideraciones() {
		return consideraciones;
	}
	public void setConsideraciones(String consideraciones) {
		this.consideraciones = consideraciones;
	}
	public List<CondicionEspecial> getCondicionesEspeciales() {
		return condicionesEspeciales;
	}
	public void setCondicionesEspeciales(
			List<CondicionEspecial> condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}
	public boolean isRefrigerada() {
		return refrigerada;
	}
	public void setRefrigerada(boolean refrigerada) {
		this.refrigerada = refrigerada;
	}
	
}
