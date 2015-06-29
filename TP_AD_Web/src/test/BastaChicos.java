package test;

import impl.cargas.Carga;
import impl.cargas.EstadoCarga;
import impl.cargas.TipoCarga;
import impl.clientes.Particular;
import impl.clientes.Receptor;
import impl.misc.Coordenada;
import impl.misc.Tamano;
import impl.misc.Ubicacion;
import impl.personal.Empleado;
import impl.personal.TipoPuesto;
import impl.productos.CondicionEspecial;
import impl.productos.ItemProducto;
import impl.productos.Producto;
import impl.productos.TipoFragilidad;
import impl.productos.TipoTratamiento;
import impl.sucursales.Sucursal;
import impl.vehiculos.PlanMantenimiento;
import impl.vehiculos.PlanMantenimientoKilometraje;
import impl.vehiculos.Proveedor;
import impl.vehiculos.TipoVehiculo;
import impl.vehiculos.VehiculoLocal;
import impl.viajes.CompaniaSeguro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Document;

import persistence.ClienteDAO;
import persistence.CompaniaSeguroDAO;
import persistence.EmpleadoDAO;
import persistence.PlanMantenimientoDAO;
import persistence.ProveedorDAO;
import persistence.SucursalDAO;
import persistence.UbicacionDAO;
import persistence.VehiculoDAO;
import persistence.ViajeDAO;
import util.Utilities;
import views.cargas.CargaView;
import views.productos.ItemProductoView;
import views.productos.ProductoView;
import views.vehiculos.PlanMantenimientoView;
import views.vehiculos.VehiculoExternoView;
import views.vehiculos.VehiculoLocalView;
import views.viajes.SeguroView;
import controllers.ControladorPrincipal;

@SuppressWarnings("all")
public class BastaChicos {
	private static ControladorPrincipal controlador;

	public static void main(String[] args) {
		try {
			controlador = ControladorPrincipal.getInstance();
			//testAltaCargaLocal();
			testAltaCargaInternacional();
			testXml();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void testXml() throws Exception {
		Document doc = ViajeDAO.getInstance().getUltimoViaje().generarXml();
		Utilities.printXml(doc);
		//Utilities.saveXml(doc);
	}

	private static void testAltaCargaLocal() throws Exception {
		System.out.println("/* ALTA CARGA LOCAL */");
		Particular particular = crearParticular();
		Sucursal sucursal = crearSucursal();
		Empleado empleado = crearEmpleado();
		CompaniaSeguro companiaSeguro = crearCompaniaSeguro();
		sucursal.agregarVehiculo(crearVehiculoLocalView(), crearPlanMantenimientoKilometrajeView(), empleado.getId());
		controlador.altaCarga(sucursal.getId(), particular.getId(), crearCargaView(), false);
	}

	private static void testAltaCargaInternacional() throws Exception {
		System.out.println("/* ALTA CARGA INTERNACIONAL */");
		Particular particular = crearParticular();
		Sucursal sucursal = crearSucursal();
		CompaniaSeguro companiaSeguro = crearCompaniaSeguro();
		Proveedor proveedor = crearProveedor();
		controlador.altaVehiculoExterno(proveedor.getId(), crearVehiculoExternoView());
		controlador.altaCarga(sucursal.getId(), particular.getId(), crearCargaView(), true);
	}

	private static Carga crearCarga() {
		Carga c = new Carga();
		c.setDestino(crearUbicacion());
		c.setEstadoCarga(EstadoCarga.EN_VIAJE);
		c.setFechaMaximaEntrega(new Date());
		c.setFechaProbableEntrega(new Date());
		c.setManifiesto(randomString());
		c.setOrigen(crearUbicacion());
		c.setTipo(TipoCarga.BOLSA);
		Collection<ItemProducto> p = new ArrayList<ItemProducto>();
		for (int i = 0; i < 5; i++) {
			p.add(new ItemProducto(crearProducto(), (float) randomInteger()));
		}
		c.setProductos(p);
		return c;
	}

	private static CargaView crearCargaView() {
		Collection<ItemProductoView> p = new ArrayList<ItemProductoView>();
		for (int i = 0; i < 5; i++) {
			p.add(new ItemProductoView(crearProductoView(), (float) randomInteger()));
		}
		CargaView cv = new CargaView(TipoCarga.BIDON.toString(), Utilities.invParseDate(new Date()), Utilities.invParseDate(new Date()),
				randomString(), crearUbicacion().getView(), crearUbicacion().getView(), EstadoCarga.EN_DEPOSITO.toString(), p, true);
		return cv;
	}

	private static Producto crearProducto() {
		Producto p = new Producto();
		p.setApilable(randomInteger());
		List<CondicionEspecial> c = new ArrayList<CondicionEspecial>();
		c.add(CondicionEspecial.SEGURIDAD);
		p.setCondicionesEspeciales(c);
		p.setConsideraciones(randomString());
		p.setFragilidad(TipoFragilidad.FRAGIL);
		p.setManipulacion(randomString());
		p.setMaterial(randomString());
		p.setNombre(randomString());
		p.setPeso((float) randomInteger());
		p.setRefrigerada(false);
		p.setTratamiento(TipoTratamiento.INOCUO);
		p.setTamano(crearTamano());
		return p;
	}

	private static ProductoView crearProductoView() {
		ProductoView p = new ProductoView();
		p.setApilable(randomInteger());
		p.setConsideraciones(randomString());
		p.setFragilidad(TipoFragilidad.FRAGIL.toString());
		p.setManipulacion(randomString());
		p.setMaterial(randomString());
		p.setNombre(randomString());
		p.setPeso((float) randomInteger());
		p.setRefrigerada(false);
		p.setTratamiento(TipoTratamiento.INOCUO.toString());
		p.setTamano(crearTamano().getView());
		return p;
	}

	private static Particular crearParticular() {
		Particular p = new Particular();
		p.setApellido(randomString());
		p.setDni(randomString());
		p.setNombre(randomString());
		List<Receptor> r = new ArrayList<Receptor>();
		for (int i = 0; i < 3; i++) {
			r.add(crearReceptor());
		}
		p.setReceptores(r);
		p.setId(ClienteDAO.getInstance().insert(p));
		return p;
	}

	private static Receptor crearReceptor() {
		Receptor r = new Receptor();
		r.setApellido(randomString());
		r.setDni(randomString());
		r.setNombre(randomString());
		r.setUbicacion(crearUbicacion());
		return r;
	}

	private static Tamano crearTamano() {
		Tamano t = new Tamano();
		t.setAlto((float) randomInteger());
		t.setAncho((float) randomInteger());
		t.setProfundidad((float) randomInteger());
		return t;
	}

	private static Ubicacion crearUbicacion() {
		Ubicacion u = new Ubicacion();
		u.setAltura(randomString());
		u.setCalle(randomString());
		u.setCiudad(randomString());
		u.setDepartamento(randomString());
		u.setPais(randomString());
		u.setProvincia(randomString());
		u.setCoordenadaDestino(crearCoordenada());
		u.setPiso(randomString());
		u.setId(UbicacionDAO.getInstance().insert(u));
		return u;
	}

	private static Coordenada crearCoordenada() {
		Coordenada c = new Coordenada();
		c.setLatitud((float) randomInteger());
		c.setLongitud((float) randomInteger());
		return c;
	}

	private static Sucursal crearSucursal() {
		Sucursal s = new Sucursal();
		s.setNombre(randomString());
		s.setUbicacion(crearUbicacion());
		s.setId(SucursalDAO.getInstance().insert(s));
		return s;
	}

	private static VehiculoLocal crearVehiculoLocal() {
		VehiculoLocal v = new VehiculoLocal();
		v.setEmpleado(crearEmpleado());
		v.setPatente(randomString());
		v.setPeso((float) randomInteger());
		v.setTamano(crearTamano());
		v.setTara((float) randomInteger());
		v.setTarifa((float) randomInteger());
		v.setTipo(TipoVehiculo.CAMION_CON_CAJA_REFRIGERADO);
		v.setVencimientoGarantia(new Date());
		v.setPlanMantenimiento(crearPlanMantenimientoKilometraje());
		v.setId(VehiculoDAO.getInstance().insert(v));
		return v;
	}

	private static VehiculoLocalView crearVehiculoLocalView() {
		VehiculoLocalView v = new VehiculoLocalView();
		v.setPatente(randomString());
		v.setPeso((float) randomInteger());
		v.setTamano(crearTamano().getView());
		v.setTara((float) randomInteger());
		v.setTarifa((float) randomInteger());
		v.setTipo(TipoVehiculo.CAMION_CON_CAJA_REFRIGERADO.toString());
		v.setVencimientoGarantia(Utilities.invParseDate(new Date()));
		return v;
	}

	private static PlanMantenimiento crearPlanMantenimientoKilometraje() {
		PlanMantenimiento p = new PlanMantenimientoKilometraje();
		p.setFechaFabricacion(new Date());
		p.setKilometraje((float) randomInteger());
		// tareas?
		p.setId(PlanMantenimientoDAO.getInstance().insert(p));
		return p;
	}

	private static PlanMantenimientoView crearPlanMantenimientoKilometrajeView() {
		PlanMantenimientoView p = new PlanMantenimientoView();
		p.setTipoPlan("kilometraje");
		p.setPuntoControl((float) randomInteger());
		p.setIntervaloMantenimiento(randomInteger());
		return p;
	}

	private static Empleado crearEmpleado() {
		Empleado e = new Empleado();
		e.setApellido(randomString());
		e.setCuit(randomString());
		e.setDni(randomString());
		e.setFechaNacimiento(new Date());
		e.setNombre(randomString());
		e.setPuesto(TipoPuesto.CHOFER);
		e.setId(EmpleadoDAO.getInstance().insert(e));
		return e;
	}

	private static CompaniaSeguro crearCompaniaSeguro() {
		CompaniaSeguro cs = new CompaniaSeguro();
		cs.setCuil(randomString());
		cs.setNombre(randomString());
		cs.setId(CompaniaSeguroDAO.getInstance().insert(cs));
		for (TipoCarga tc : TipoCarga.values()) {
			SeguroView s = new SeguroView(randomString(), tc.toString(), (float) randomInteger());
			cs.agregarSeguro(s);
		}
		return cs;
	}

	private static Proveedor crearProveedor() {
		Proveedor p = new Proveedor();
		p.setCuit(randomString());
		p.setNombre(randomString());
		p.setId(ProveedorDAO.getInstance().insert(p));
		return p;
	}

	private static VehiculoExternoView crearVehiculoExternoView() {
		VehiculoExternoView v = new VehiculoExternoView();
		v.setPatente(randomString());
		v.setPeso((float) randomInteger() * 10000);
		v.setTamano(crearTamano().getView());
		v.setTara((float) randomInteger());
		v.setTarifa((float) randomInteger());
		v.setTipo(TipoVehiculo.CARRIER.toString());
		return v;
	}

	private static Integer randomInteger() {
		Random rand = new Random();
		Integer max = 100;
		Integer min = 1;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private static String randomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
