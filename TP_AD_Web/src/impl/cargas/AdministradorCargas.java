package impl.cargas;

import impl.clientes.Cliente;
import impl.productos.Producto;
import impl.sucursales.Sucursal;
import impl.viajes.AdministradorViajes;
import impl.viajes.ParadaIntermedia;
import impl.viajes.ViajeOptimo;

import java.util.ArrayList;
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

	public Integer altaCarga(Integer idSucursal, Integer idCliente, CargaView c, Boolean esInternacional) throws Exception {
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
			asignarCargaAViajeOptimo(carga, esInternacional);
			return carga.getId();
		} else {
			throw new Exception("No existe cliente o sucursal con el ID ingresado.");
		}
	}

	public List<CargaView> obtenerCargasView() {
		List<CargaView> cargas = new ArrayList<CargaView>();
		for (Carga carga : cargaDao.getAll()) {
			cargas.add(carga.getView());
		}
		return cargas;
	}

	private void asignarCargaAViajeOptimo(Carga c, Boolean esInternacional) throws Exception {
		ViajeOptimo viajeOptimo = admVi.obtenerViajeOptimo(c);
		if (viajeOptimo != null) {
			viajeOptimo.getViaje().agregarCarga(c);
			viajeOptimo.getViaje()
					.agregarParadaIntermedia(new ParadaIntermedia(c.getDestino(), c.getFechaProbableEntrega()));
		} else {
			admVi.crearViajeEnBaseACarga(c, esInternacional);
		}
	}

	public Carga obtenerCarga(Integer idCarga) {
		return cargaDao.get(idCarga);
	}
}
