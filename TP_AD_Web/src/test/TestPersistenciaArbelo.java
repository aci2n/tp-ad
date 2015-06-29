/*package test;

import impl.cargas.Carga;
import impl.cargas.EstadoCarga;
import impl.cargas.TipoCarga;
import impl.clientes.CuentaCorriente;
import impl.clientes.Empresa;
import impl.clientes.Particular;
import impl.cobranzas.Factura;
import impl.cobranzas.Pago;
import impl.misc.Coordenada;
import impl.misc.Tamano;
import impl.misc.Ubicacion;
import impl.personal.Empleado;
import impl.personal.TipoPuesto;
import impl.productos.CondicionEspecial;
import impl.productos.Producto;
import impl.productos.TipoFragilidad;
import impl.productos.TipoTratamiento;
import impl.sucursales.DistanciaEntreSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.PlanMantenimientoKilometraje;
import impl.vehiculos.Proveedor;
import impl.vehiculos.Tarea;
import impl.vehiculos.TipoVehiculo;
import impl.vehiculos.VehiculoExterno;
import impl.vehiculos.VehiculoLocal;
import impl.viajes.CompaniaSeguro;
import impl.viajes.ParadaIntermedia;
import impl.viajes.Seguro;
import impl.viajes.Viaje;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import persistence.HibernateUtil;
import views.clientes.ReceptorView;
import views.misc.TamanoView;
import views.vehiculos.PlanMantenimientoView;

@SuppressWarnings("all")
public class TestPersistenciaArbelo {
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {
		try {
			crearCosas();
			// System.out.println(((Sucursal)levantarAlgo(Sucursal.class,
			// 1)).getEmpleados().get(0).getApellido());
		} catch (Exception e) {
			System.out.println("\nexploto algo\n");
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void crearCosas() throws Exception {
		Session s = sf.openSession();
		Ubicacion u = new Ubicacion("ayy", "lmao", "rio cuarto", "sobremonte",
				"982", "1", "A", new Coordenada(45, 55));
		Seguro seguro = new Seguro();	
		seguro.setNombre("seguro contra quesos");
		seguro.setTarifa(55f);
		seguro.setTipoCarga(TipoCarga.BOLSA); // devolvela
		Proveedor proveedor = new Proveedor("123", "alvaro");
		CompaniaSeguro companiaSeguro = new CompaniaSeguro();
		companiaSeguro.setCuil("123");
		companiaSeguro.setNombre("compania1");
		companiaSeguro.agregarSeguro(seguro.getView());
		ParadaIntermedia pi = new ParadaIntermedia();
		pi.setLlegada(new Date());
		pi.setChecked(true);
		pi.setUbicacion(u);
		VehiculoLocal vehiculoLocal = new VehiculoLocal();
		vehiculoLocal.setPatente("ABC-123");
		vehiculoLocal.setPeso(456f);
		vehiculoLocal.setTamano(new Tamano(new TamanoView(5f, 8f, 9f)));
		vehiculoLocal.setTara(5f);
		vehiculoLocal.setTarifa(8f);
		vehiculoLocal.setTipo(TipoVehiculo.CAMION_CON_TANQUE);
		vehiculoLocal.setVencimientoGarantia(new Date());
		VehiculoExterno vehiculoExterno = new VehiculoExterno();
		vehiculoExterno.setPatente("DEF-456");
		vehiculoExterno.setPeso(456f);
		vehiculoExterno.setTamano(new Tamano(new TamanoView(8f, 45f, 22f)));
		vehiculoExterno.setTara(8f);
		vehiculoExterno.setTarifa(89f);
		vehiculoExterno.setTipo(TipoVehiculo.TRACTOR);
		vehiculoExterno.setProveedor(proveedor);
		Producto producto = new Producto();
		producto.setApilable(2);
		producto.setConsideraciones("no comprar");
		producto.setFragilidad(TipoFragilidad.NORMAL);
		producto.setManipulacion("no manipular");
		producto.setPeso(456f);
		producto.setMaterial("jajajajsacja");
		producto.setRefrigerada(true);
		producto.setTratamiento(TipoTratamiento.PELIGROSO);
		producto.setTamano(new Tamano(new TamanoView(2f, 8f, 9f)));
		producto.agregarCondicionEspecial(CondicionEspecial.SEGURIDAD.getCondicion());
		producto.setNombre("queso");
		CuentaCorriente cuentaCorriente = new CuentaCorriente();
		cuentaCorriente.setDepositoPrevio(true);
		cuentaCorriente.setMontoActual(45f);
		cuentaCorriente.setMontoAutorizado(456f);
		Empresa empresa = new Empresa();
		empresa.setCuentaCorriente(cuentaCorriente);
		empresa.setNombre("menem");
		empresa.setRegular(false);
		empresa.agregarProducto(producto);
		ReceptorView receptor = new ReceptorView();
		receptor.setApellido("asfdas");
		receptor.setDni("asdas");
		receptor.setNombre("asdasd");
		receptor.setUbicacion(u.getView());
		Particular particular = new Particular();
		particular.setApellido("calace");
		particular.setDni("213123");
		particular.setNombre("arbelo");
		particular.agregarReceptor(receptor);
		Carga carga = new Carga();
		carga.setCliente(empresa);
		carga.setDestino(u);
		carga.setEstadoCarga(EstadoCarga.EN_VIAJE);
		carga.setFechaMaximaEntrega(new Date());
		carga.setFechaProbableEntrega(new Date());
		carga.setManifiesto("soy un manifiesto");
		carga.setOrigen(u);
		carga.setTipo(TipoCarga.GRANEL);
		carga.agregarItemProducto(producto, 5f);
		Factura factura = new Factura();
		factura.setCarga(carga);
		factura.setMontoTotal(456f);
		factura.realizarCobroParcial(new Date(), 200f);
		factura.setTipoFactura("A");
		factura.setFechaCreacion(new Date());
		Empleado empleado = new Empleado();
		empleado.setApellido("hola");
		empleado.setCuit("soy");
		empleado.setDni("german");
		empleado.setFechaNacimiento(new Date());
		empleado.setNombre("!");
		empleado.setPuesto(TipoPuesto.GERENTE);
		PlanMantenimientoKilometraje plan = new PlanMantenimientoKilometraje(20f);
		plan.setFechaFabricacion(new Date());
		plan.setKilometraje(2321f);
		Tarea tarea = new Tarea();
		tarea.setFechaDevolucion(new Date());
		tarea.setFechaEntrega(new Date());
		tarea.setKilometraje(123f);
		plan.agregarTarea(tarea.getView());
		vehiculoLocal.setPlanMantenimiento(plan);

		s.beginTransaction();
		s.save(u);
		s.save(seguro);
		s.save(proveedor);
		s.save(companiaSeguro);
		s.save(pi);
		s.save(vehiculoLocal);
		s.save(vehiculoExterno);
		s.save(producto);
		s.save(empresa);
		s.save(receptor);
		s.save(particular);
		s.save(carga);
		s.save(factura);
		s.save(empleado);
		
		Sucursal sucursal = new Sucursal();
		sucursal.setNombre("cruzeiro fc");
		sucursal.setUbicacion(u);
		sucursal.agregarCarga(carga);
		sucursal.agregarVehiculo(vehiculoLocal.getView(), new PlanMantenimientoView("kilometraje"), empleado.getId());
		sucursal.agregarEmpleado(empleado.getView());
		Pago pago = new Pago();
		pago.setProveedor(proveedor);
		pago.setFecha(new Date());
		pago.setMonto(123f);
		pago.setEstado(true);
		Sucursal sucursalB = sucursalB();
		DistanciaEntreSucursales des = new DistanciaEntreSucursales();
		des.setCosto(456f);
		des.setDistanciaEnKm(7889f);
		des.setSucursalA(sucursal);
		des.setSucursalB(sucursalB);
		des.setDuracionEnHoras(5f);
		Viaje viaje = new Viaje();
		viaje.setVehiculo(vehiculoLocal);
		viaje.agregarCarga(carga);
		viaje.setDestino(u);
		viaje.setOrigen(sucursalB.getUbicacion());
		viaje.setEstaAtrasado(true);
		viaje.setFechaSalida(new Date());
		viaje.setFechaLlegada(new Date());
		viaje.setSeguro(seguro);
		viaje.agregarCondicionEspecial(CondicionEspecial.TEMPERATURA.getCondicion());
		viaje.agregarParadaIntermedia(pi);
		s.save(sucursal);
		s.save(pago);
		s.save(sucursalB.getUbicacion());
		s.save(sucursalB);
		s.save(des);
		s.save(viaje);
		s.flush();
		s.getTransaction().commit();
		s.close();
	}

	private static Object levantarAlgo(Class<?> className, int id) {
		Session s = sf.openSession();
		Object o = (Object) s.get(className, id);
		s.close();
		return o;
	}

	private static Sucursal sucursalB() {
		Sucursal sucursalB = new Sucursal();
		sucursalB.setNombre("sucursal b");
		Ubicacion uSucursalB = new Ubicacion("ay123y", "l2222o", "rio cudaso",
				"sobreasdasdnte", "92132", "sad", "asd", new Coordenada(800,
						454));
		sucursalB.setUbicacion(uSucursalB);
		return sucursalB;
	}
}
*/
