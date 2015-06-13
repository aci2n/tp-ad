package model.impl.productos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.misc.Tamano;

@Entity
@Table(name = "Productos")
@AttributeOverride(name = "id", column = @Column(name = "id_producto"))
public class Producto extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2506118120974790841L;
	private static final String[] materialesRestringidos = { "Material1",
			"Material2", "Material3" };

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "tipo_fragilidad")
	@Enumerated(value = EnumType.STRING)
	private TipoFragilidad fragilidad;

	@Column(name = "tipo_tratamiento")
	@Enumerated(value = EnumType.STRING)
	private TipoTratamiento tratamiento;

	@Embedded
	private Tamano tamano;

	@Column(name = "peso")
	private Float peso;

	@Column(name = "apilable")
	private Integer apilable;

	@Column(name = "manipulacion")
	private String manipulacion;

	@Column(name = "material")
	private String material;

	@Column(name = "consideraciones")
	private String consideraciones;

	@ElementCollection(targetClass = CondicionEspecial.class)
	@CollectionTable(name = "Productos_CondicionesEspeciales", joinColumns = @JoinColumn(name = "id_producto"))
	@Column(name = "condicion_especial")
	@Enumerated(EnumType.STRING)
	private List<CondicionEspecial> condicionesEspeciales;

	@Column(name = "refrigerada")
	private boolean refrigerada;

	public Producto() {

	}

	public Producto(String nombre, Float peso, Tamano tamano,
			TipoFragilidad fragilidad, Integer apilable, String manipulacion,
			String material, TipoTratamiento tratamiento,
			String consideraciones,
			List<CondicionEspecial> condicionesEspeciales) {

		this.nombre = nombre;
		this.peso = peso;
		this.tamano = tamano;
		this.fragilidad = fragilidad;
		this.apilable = apilable;
		this.manipulacion = manipulacion;
		this.material = material;
		this.tratamiento = tratamiento;
		this.consideraciones = consideraciones;
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Tamano getTamano() {
		return tamano;
	}

	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}

	public TipoFragilidad getFragilidad() {
		return fragilidad;
	}

	public void setFragilidad(TipoFragilidad fragilidad) {
		this.fragilidad = fragilidad;
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

	public TipoTratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(TipoTratamiento tratamiento) {
		this.tratamiento = tratamiento;
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

	public Float calcularFactorProducto() {
		Float factorBase = 0f;
		factorBase += tamano.calcularVolumen() * 0.001f; // cada 10m cubicos
															// aumenta 1%
		if (fragilidad != null)
			factorBase += fragilidad.getFactorFragilidad();
		if (tratamiento != null)
			factorBase += tratamiento.getFactorTratamiento();
		factorBase += peso * 0.0004f; // cada 100 kilos aumenta 4%
		factorBase += calcularFactorCondicionesEspeciales();
		if (refrigerada)
			factorBase += 0.005f;
		return factorBase;
	}

	private Float calcularFactorCondicionesEspeciales() {
		Float total = 0f;
		for (CondicionEspecial ce : condicionesEspeciales) {
			total += ce.getFactorCondicion();
		}
		return total;
	}

	public void agregarCondicionEspecial(CondicionEspecial condicion) {
		if (condicionesEspeciales == null)
			condicionesEspeciales = new ArrayList<CondicionEspecial>();
		condicionesEspeciales.add(condicion);
	}

	public boolean esRestringido() {
		for (int i = 0; i < materialesRestringidos.length; i++) {
			if (material.equals(materialesRestringidos[i])) {
				return true;
			}
		}
		return false;
	}
}
