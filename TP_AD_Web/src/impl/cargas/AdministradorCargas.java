package impl.cargas;

import impl.clientes.Cliente;
import impl.productos.Producto;
import impl.sucursales.Sucursal;
import impl.viajes.AdministradorViajes;
import impl.viajes.ItemCarga;
import impl.viajes.ParadaIntermedia;
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
			Date salidaCarga = fechaMaximaDeSalida(carga.getCarga(), viaje);
			if (salidaMaxima == null || salidaMaxima.after(salidaCarga)) {
				salidaMaxima = salidaCarga;
			}
		}
		
		return salidaMaxima;
	}
	
	public Date fechaMaximaDeSalida(Carga carga, Viaje viaje) {
		float distancia = 0;

		if (viaje.cantidadParadasIntemedias() == 0) {
			distancia = viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getDestino());
		} else if (viaje.cantidadParadasIntemedias() == 1){
			distancia = viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getParadasIntermedias().get(0).getUbicacion());
			if (carga.getDestino().tieneMismasCoordenadas(viaje.getDestino())) {
				distancia += viaje.getParadasIntermedias().get(0).getUbicacion().calcularDistanciaEnKilometros(viaje.getDestino());
			}
		} else {
			distancia += viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getParadasIntermedias().get(0).getUbicacion());
			if (!viaje.getParadasIntermedias().get(0).getUbicacion().tieneMismasCoordenadas(carga.getDestino())) {
				boolean llego = false;
				for (int i = 0; i < viaje.cantidadParadasIntemedias() - 1; i++) {
					ParadaIntermedia actual = viaje.getParadasIntermedias().get(i);
					ParadaIntermedia siguiente = viaje.getParadasIntermedias().get(i + 1);
					
					distancia += actual.getUbicacion().calcularDistanciaEnKilometros(siguiente.getUbicacion());
					
					if (siguiente.getUbicacion().tieneMismasCoordenadas(carga.getDestino())) {
						llego = true;
						break;
					}
				}
				
				if (!llego) {
					distancia += viaje.getParadasIntermedias().get(viaje.cantidadParadasIntemedias() - 1).getUbicacion().calcularDistanciaEnKilometros(viaje.getDestino());
				}
			}
		}
		
		float horas = distancia / AdministradorViajes.VELOCIDAD_PROMEDIO;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(carga.getFechaMaximaEntrega());
		cal.add(Calendar.DATE, -1);
		cal.add(Calendar.HOUR, -((int) horas));
		cal.add(Calendar.MINUTE, -((int) ((horas - (int) horas) * 60)));
		
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
					.agregarParadaIntermedia(new ParadaIntermedia(c.getDestino(), c.getFechaProbableEntrega()));
		} else {
			admVi.crearViajeEnBaseACarga(c);
		}
	}

}
