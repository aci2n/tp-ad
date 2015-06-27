package views.productos;

import views.GenericView;
import views.misc.TamanoView;

public class ProductoView extends GenericView{
	private String nombre;
	private String fragilidad;
	private String tratamiento;
	private TamanoView tamano;
	private Float peso;
	private Integer apilable;
	private String manipulacion;
	private String material;
	private String consideraciones;
	private Boolean refrigerada;

	public ProductoView(String nombre, String fragilidad, String tratamiento, TamanoView tamano, Float peso, Integer apilable, String manipulacion,
			String material, String consideraciones, Boolean refrigerada) {
		this.nombre = nombre;
		this.fragilidad = fragilidad;
		this.tratamiento = tratamiento;
		this.tamano = tamano;
		this.peso = peso;
		this.apilable = apilable;
		this.manipulacion = manipulacion;
		this.material = material;
		this.consideraciones = consideraciones;
		this.refrigerada = refrigerada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFragilidad() {
		return fragilidad;
	}

	public void setFragilidad(String fragilidad) {
		this.fragilidad = fragilidad;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
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

	public Boolean getRefrigerada() {
		return refrigerada;
	}

	public void setRefrigerada(Boolean refrigerada) {
		this.refrigerada = refrigerada;
	}

}
