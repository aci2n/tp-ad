package model.impl.misc;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import model.impl.PersistentObject;

@Entity
@Table(name = "Ubicaciones")
@AttributeOverride(name = "id", column = @Column(name = "id_ubicacion"))
public class Ubicacion extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3927241277115332251L;
	@Column(name = "pais")
	private String pais;
	@Column(name = "provincia")
	private String provincia;
	@Column(name = "ciudad")
	private String ciudad;
	@Column(name = "calle")
	private String calle;
	@Column(name = "altura")
	private String altura;
	@Column(name = "piso")
	private String piso;
	@Column(name = "departamento")
	private String departamento;
	@Embedded
	private Coordenada coordenadaDestino;

	public Ubicacion(String pais, String provincia, String ciudad,
			String calle, String altura, String piso, String departamento,
			Coordenada coordenadaDestino) {
		this.pais = pais;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.coordenadaDestino = coordenadaDestino;
	}

	public Ubicacion() {

	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Coordenada getCoordenadaDestino() {
		return coordenadaDestino;
	}

	public void setCoordenadaDestino(Coordenada coordenadaDestino) {
		this.coordenadaDestino = coordenadaDestino;
	}

	public float calcularDistanciaEnKilometros(Ubicacion ubicacion) {
		return this.coordenadaDestino.calcularDistanciaEnKilometros(ubicacion
				.getCoordenadaDestino());
	}
}
