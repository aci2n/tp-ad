package impl.clientes;

import impl.productos.Producto;

import java.util.ArrayList;
import java.util.List;

import persistence.ClienteDAO;
import views.clientes.EmpresaView;
import views.clientes.ParticularView;
import views.clientes.ReceptorView;

public class AdministradorClientes {
	private static AdministradorClientes instance;
	private List<Cliente> clientes;
	private List<Pago> pagos;
	private List<Producto> productos;

	private AdministradorClientes() {
		this.clientes = new ArrayList<Cliente>();
		this.pagos = new ArrayList<Pago>();
		this.productos = new ArrayList<Producto>();
	}

	public static AdministradorClientes getInstance() {
		if (instance == null)
			instance = new AdministradorClientes();
		return instance;
	}

	public Integer altaClienteEmpresa(String nombre) {
		Empresa e = new Empresa(nombre);
		return e.getId();
	}

	public Integer altaClienteParticular(String dni, String nombre,
			String apellido) {
		Particular p = new Particular(dni, nombre, apellido);
		return p.getId();
	}

	public void agregarReceptor(Integer id, ReceptorView r) throws Exception {
		Particular particular = ClienteDAO.getInstance()
				.obtenerClienteParticular(id);
		if (particular != null) {
			particular.agregarReceptor(r);
		} else {
			throw new Exception("El cliente particular ingresado no existe.");
		}
	}

	public void bajaCliente(Integer id) {
		for (Cliente c : clientes) {
			if (c.getId().equals(id)) {
				clientes.remove(c);
				return;
			}
		}
	}

	public void asignarCuentaCorriente(Integer id, Float montoActual,
			Float montoAutorizado) throws Exception {
		if (esClienteEmpresa(id)) {
			Empresa c = (Empresa) obtenerCliente(id);
			c.setCuentaCorriente(new CuentaCorriente(montoActual,
					montoAutorizado));
		} else
			throw new Exception("El cliente con id" + id + " no es una empresa");
	}

	public boolean esClienteEmpresa(Integer id) {
		for (Cliente c : clientes)
			if (c.getId().equals(id) && c.getClass().equals(Empresa.class))
				return true;
		return false;
	}

	public Cliente obtenerCliente(Integer id) {
		for (Cliente c : clientes)
			if (c.getId().equals(id))
				return c;
		return null;
	}

	private Producto obtenerProducto(int codigoProducto) {
		for (Producto p : productos)
			if (p.getId() == codigoProducto)
				return p;
		return null;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<ParticularView> obtenerClientesParticulares() {

		List<ParticularView> particulares = new ArrayList<ParticularView>();

		for (Particular p : ClienteDAO.getInstance()
				.getAllClientesParticulares())
			particulares.add(p.getView());

		return particulares;
	}

	public List<EmpresaView> obtenerClientesEmpresas() {

		List<EmpresaView> empresas = new ArrayList<EmpresaView>();

		for (Empresa p : ClienteDAO.getInstance().getAllClientesEmpresas())
			empresas.add(p.getView());

		return empresas;
	}
}
