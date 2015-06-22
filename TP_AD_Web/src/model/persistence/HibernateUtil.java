package model.persistence;

import model.impl.cargas.Carga;
import model.impl.clientes.Cliente;
import model.impl.clientes.CobroParcial;
import model.impl.clientes.CuentaCorriente;
import model.impl.clientes.Empresa;
import model.impl.clientes.Factura;
import model.impl.clientes.Pago;
import model.impl.clientes.Particular;
import model.impl.clientes.Receptor;
import model.impl.misc.Coordenada;
import model.impl.misc.Tamano;
import model.impl.misc.Ubicacion;
import model.impl.personal.Empleado;
import model.impl.productos.ItemProducto;
import model.impl.productos.Producto;
import model.impl.sucursales.DistanciaEntreSucursales;
import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.PlanMantenimiento;
import model.impl.vehiculos.PlanMantenimientoKilometraje;
import model.impl.vehiculos.PlanMantenimientoKilometrajeRelativo;
import model.impl.vehiculos.PlanMantenimientoTemporal;
import model.impl.vehiculos.Proveedor;
import model.impl.vehiculos.Tarea;
import model.impl.vehiculos.Vehiculo;
import model.impl.vehiculos.VehiculoExterno;
import model.impl.vehiculos.VehiculoLocal;
import model.impl.viajes.CompaniaSeguro;
import model.impl.viajes.ItemCarga;
import model.impl.viajes.ParadaIntermedia;
import model.impl.viajes.Seguro;
import model.impl.viajes.Viaje;

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
