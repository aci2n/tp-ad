package model.impl.clientes;

import java.util.ArrayList;
import java.util.List;

import model.impl.productos.Producto;

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

	public void altaClienteEmpresa(String nombre) {
		Cliente.persistirCliente(new Empresa(nombre));
	}

	public void altaClienteParticular(String dni, String nombre, String apellido) {
		Cliente.persistirCliente(new Particular(dni, nombre, apellido));
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
}
