package model.impl.sucursales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.Carga;
import model.impl.misc.Ubicacion;
import model.impl.personal.Empleado;
import model.impl.vehiculos.TipoVehiculo;
import model.impl.vehiculos.VehiculoLocal;
import model.impl.viajes.Seguro;
import model.impl.viajes.Viaje;
import model.persistence.SucursalDAO;
import model.views.VehiculoLocalView;

@Entity
@Table(name = "Sucursales")
@AttributeOverride(name = "id", column = @Column(name = "id_sucursal"))
public class Sucursal extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2534989790774285551L;
	@Column(name = "nombre")
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "id_ubicacion")
	private Ubicacion ubicacion;
	@OneToMany
	@JoinColumn(name = "id_sucursal")
	private List<Carga> cargas;
	@OneToMany
	@JoinColumn(name = "id_sucursal")
	private List<Empleado> empleados;
	@OneToMany
	@JoinColumn(name = "id_sucursal")
	private List<VehiculoLocal> vehiculos;

	public Sucursal() {
	}

	public Sucursal(String nombre, Ubicacion ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		vehiculos = new ArrayList<VehiculoLocal>();
		cargas = new ArrayList<Carga>();
		empleados = new ArrayList<Empleado>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<VehiculoLocal> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<VehiculoLocal> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Carga retirarCarga(Integer codigoCarga) {
		for (Carga carga : this.cargas) {
			if (carga.getId().equals(codigoCarga)) {
				return carga;
			}
		}
		return null;
	}

	public VehiculoLocal obtenerVehiculo(String patente) {
		for (VehiculoLocal vehiculo : vehiculos) {
			if (vehiculo.getPatente().equals(patente)) {
				return vehiculo;
			}
		}
		return null;
	}

	public Empleado obtenerEmpleado(String cuit) {
		for (Empleado empleado : empleados) {
			if (empleado.getCuit().equals(cuit)) {
				return empleado;
			}
		}
		return null;
	}

	public void crearViaje() {
	}

	public void asignarSeguroAViaje(Seguro seguro, Viaje viaje) {
	}

	public void agregarEmpleado(Empleado e) {
		if (empleados == null)
			empleados = new ArrayList<Empleado>();
		this.empleados.add(e);
	}

	public Integer agregarVehiculo(VehiculoLocalView v) {
		if (vehiculos == null)
			vehiculos = new ArrayList<VehiculoLocal>();
		VehiculoLocal vehiculo = new VehiculoLocal(v.getPatente(), v.getTamano(), v.getPeso(), v.getTara(), v.getTarifa(), TipoVehiculo.valueOf(v
				.getTipo()), parseDate(v.getVencimientoGarantia()));
		vehiculos.add(vehiculo);
		SucursalDAO.getInstance().update(this);
		return vehiculo.getId();
	}

	private Date parseDate(String fecha) {
		Date d = new Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			d = formatter.parse(fecha);
		} catch (Exception e) {
			// mandar saludos
		}
		return d;
	}

	public boolean existeVehiculo(String patente) {
		for (VehiculoLocal v : vehiculos) {
			if (v.getPatente().equals(patente)) {
				return true;
			}
		}
		return false;
	}

	public Carga obtenerCarga(int codigoCarga) {
		for (Carga c : cargas)
			if (c.getId() == codigoCarga)
				return c;
		return null;
	}

	public boolean existeCarga(int codigoCarga) {
		for (Carga c : cargas)
			if (c.getId() == codigoCarga)
				return true;
		return false;
	}

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	public void agregarCarga(Carga carga) {
		if (cargas == null)
			cargas = new ArrayList<Carga>();
		cargas.add(carga);
	}
}
