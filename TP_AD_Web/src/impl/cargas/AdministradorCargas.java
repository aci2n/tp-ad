package impl.cargas;

import impl.clientes.Cliente;
import impl.productos.Producto;
import impl.sucursales.AdministradorSucursales;
import impl.sucursales.Sucursal;
import impl.viajes.AdministradorViajes;
import impl.viajes.ItemCarga;
import impl.viajes.Viaje;
import impl.viajes.ViajeOptimo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import persistence.CargaDAO;
import persistence.ClienteDAO;
import persistence.SucursalDAO;
import views.cargas.CargaView;
import views.productos.ItemProductoView;
import views.viajes.ParadaIntermediaView;

public class AdministradorCargas {
	private static AdministradorCargas instance;
	private SucursalDAO sucursalDao;
	private ClienteDAO clienteDao;
	private AdministradorViajes admVi;
	private CargaDAO cargaDao;
	
	
	public static AdministradorCargas getInstance() {
		if (instance == null) 
			instance = new AdministradorCargas();
		return instance;
	}
	
	private AdministradorCargas() {
		this.sucursalDao = SucursalDAO.getInstance();
		this.clienteDao = ClienteDAO.getInstance();
		this.admVi = AdministradorViajes.getInstance();
		this.cargaDao = CargaDAO.getInstance();
	}
	
	public Integer altaCarga(Integer idSucursal, Integer idCliente, CargaView c) throws Exception {
		for (ItemProductoView ipv : c.getProductos()) {
			if (!Producto.esMaterialPermitido(ipv.getProducto().getMaterial())) {
				throw new Exception("La carga tiene un material prohibido.");
			}
		}
		Cliente cli = clienteDao.get(idCliente);
		Sucursal suc = sucursalDao.get(idSucursal);
		if (cli != null && suc != null) {
			Carga carga = new Carga(c, cli);
			suc.agregarCarga(carga);
			asignarCargaAViajeOptimo(carga);
			return carga.getId();
		} else {
			throw new Exception("No existe cliente o sucursal con el ID ingresado.");
		}
	}
	
	public Date fechaMaximaDeSalida(Viaje viaje) {
		Date salidaMaxima = null;
		
		for (ItemCarga carga : viaje.getCargas()) {
			Date salidaCarga = fechaMaximaDeSalida(carga.getCarga());
			if (salidaMaxima == null || salidaMaxima.after(salidaCarga)) {
				salidaMaxima = salidaCarga;
			}
		}
		
		return salidaMaxima;
	}
	
	public Date fechaMaximaDeSalida(Carga carga) {
		Calendar cal = Calendar.getInstance();
		
		AdministradorSucursales admSuc = AdministradorSucursales.getInstance();
		Sucursal origen = admSuc.obtenerSucursalCercana(carga.getOrigen());
		Sucursal destino = admSuc.obtenerSucursalCercana(carga.getDestino());
		
		float distancia = admSuc.calcularHorasEntreSucursales(origen, destino);
		
		cal.add(Calendar.HOUR, -((int) distancia));
		cal.add(Calendar.MINUTE, -((int) ((distancia - (int) distancia) * 60)));
		
		return cal.getTime();
	}
	
	public List<CargaView> obtenerCargasView() {
		List<CargaView> cargas = new ArrayList<CargaView>();
		for (Carga carga : cargaDao.getAll()) {
			cargas.add(carga.getView());
		}
		return cargas;
	}
	
	private void asignarCargaAViajeOptimo(Carga c) throws Exception {
		ViajeOptimo viajeOptimo = admVi.obtenerViajeOptimo(c);
		if (viajeOptimo != null) {
			viajeOptimo.getViaje().agregarCarga(c);
			viajeOptimo.getViaje()
					.agregarParadaIntermedia(new ParadaIntermediaView(c.getFechaProbableEntrega().toString(), c.getDestino().getView()));
		} else {
			admVi.crearViajeEnBaseACarga(c);
		}
	}

}
