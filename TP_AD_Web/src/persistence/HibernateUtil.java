package persistence;

import impl.cargas.Carga;
import impl.cargas.SeguimientoCarga;
import impl.clientes.Cliente;
import impl.clientes.CobroParcial;
import impl.clientes.CuentaCorriente;
import impl.clientes.Empresa;
import impl.clientes.Factura;
import impl.clientes.Pago;
import impl.clientes.Particular;
import impl.clientes.Receptor;
import impl.misc.Coordenada;
import impl.misc.Tamano;
import impl.misc.Ubicacion;
import impl.personal.Empleado;
import impl.productos.ItemProducto;
import impl.productos.Producto;
import impl.sucursales.DistanciaEntreSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.PlanMantenimiento;
import impl.vehiculos.PlanMantenimientoKilometraje;
import impl.vehiculos.PlanMantenimientoKilometrajeRelativo;
import impl.vehiculos.PlanMantenimientoTemporal;
import impl.vehiculos.Proveedor;
import impl.vehiculos.Tarea;
import impl.vehiculos.Vehiculo;
import impl.vehiculos.VehiculoExterno;
import impl.vehiculos.VehiculoLocal;
import impl.viajes.CompaniaSeguro;
import impl.viajes.ItemCarga;
import impl.viajes.ParadaIntermedia;
import impl.viajes.SeguimientoViaje;
import impl.viajes.Seguro;
import impl.viajes.Viaje;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {
		try {
			Configuration config = new Configuration();
			config.addAnnotatedClass(CompaniaSeguro.class);
			config.addAnnotatedClass(ParadaIntermedia.class);
			config.addAnnotatedClass(Proveedor.class);
			config.addAnnotatedClass(Seguro.class);
			config.addAnnotatedClass(Ubicacion.class);
			config.addAnnotatedClass(Coordenada.class);
			config.addAnnotatedClass(Vehiculo.class);
			config.addAnnotatedClass(VehiculoLocal.class);
			config.addAnnotatedClass(VehiculoExterno.class);
			config.addAnnotatedClass(Tamano.class);
			config.addAnnotatedClass(Producto.class);
			config.addAnnotatedClass(Empresa.class);
			config.addAnnotatedClass(CuentaCorriente.class);
			config.addAnnotatedClass(Cliente.class);
			config.addAnnotatedClass(Receptor.class);
			config.addAnnotatedClass(Particular.class);
			config.addAnnotatedClass(Carga.class);
			config.addAnnotatedClass(ItemProducto.class);
			config.addAnnotatedClass(Factura.class);
			config.addAnnotatedClass(CobroParcial.class);
			config.addAnnotatedClass(Sucursal.class);
			config.addAnnotatedClass(Empleado.class);
			config.addAnnotatedClass(Pago.class);
			config.addAnnotatedClass(DistanciaEntreSucursales.class);
			config.addAnnotatedClass(Viaje.class);
			config.addAnnotatedClass(ItemCarga.class);
			config.addAnnotatedClass(PlanMantenimientoKilometraje.class);
			config.addAnnotatedClass(PlanMantenimientoKilometrajeRelativo.class);
			config.addAnnotatedClass(PlanMantenimientoTemporal.class);
			config.addAnnotatedClass(Tarea.class);
			config.addAnnotatedClass(PlanMantenimiento.class);
			config.addAnnotatedClass(SeguimientoCarga.class);
			config.addAnnotatedClass(SeguimientoViaje.class);
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
