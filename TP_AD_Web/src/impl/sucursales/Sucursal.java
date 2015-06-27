package impl.sucursales;

import impl.PersistentObject;
import impl.cargas.Carga;
import impl.misc.Ubicacion;
import impl.personal.Empleado;
import impl.vehiculos.VehiculoLocal;
import impl.viajes.Seguro;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import persistence.SucursalDAO;
import views.personal.EmpleadoView;
import views.sucursales.SucursalView;
import views.vehiculos.PlanMantenimientoView;
import views.vehiculos.VehiculoLocalView;

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
		this(nombre, ubicacion, null, null, null);
	}

	public Sucursal(String nombre, Ubicacion ubicacion, List<Carga> cargas, List<Empleado> empleados, List<VehiculoLocal> vehiculos) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		if (cargas != null) {
			this.cargas = cargas;
		} else {
			this.cargas = new ArrayList<Carga>();
		}
		if (empleados != null) {
			this.empleados = empleados;
		} else {
			this.empleados = new ArrayList<Empleado>();
		}
		if (vehiculos != null) {
			this.vehiculos = vehiculos;
		} else {
			this.vehiculos = new ArrayList<VehiculoLocal>();
		}
		this.id = SucursalDAO.getInstance().insert(this);
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

	public Integer agregarEmpleado(EmpleadoView e) {
		if (empleados == null)
			empleados = new ArrayList<Empleado>();
		Empleado empleado = new Empleado(e.getCuit(), e.getDni(), e.getNombre(), e.getApellido(), e.getFechaNacimiento(), e.getTipo());
		empleados.add(empleado);
		return empleado.getId();
	}

	public Integer agregarVehiculo(VehiculoLocalView v, PlanMantenimientoView p) {
		if (vehiculos == null)
			vehiculos = new ArrayList<VehiculoLocal>();
		VehiculoLocal vehiculo = new VehiculoLocal(v, p);
		vehiculos.add(vehiculo);
		SucursalDAO.getInstance().update(this);
		return vehiculo.getId();
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

	public void agregarCarga(Carga c) {
		if (cargas == null)
			cargas = new ArrayList<Carga>();
		cargas.add(c);
		SucursalDAO.getInstance().update(this);
	}

	public SucursalView getView() {
		SucursalView view = new SucursalView(nombre, ubicacion.getView());
		return view;
	}
}
