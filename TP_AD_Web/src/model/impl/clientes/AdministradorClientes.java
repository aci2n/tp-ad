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
	
	public void altaClienteEmpresa(String codigoUnico, String nombre) throws Exception {
		if (obtenerCliente(codigoUnico) == null)
			clientes.add(new Empresa(codigoUnico, nombre));
		else 
			throw new Exception("Cliente con codigo "+codigoUnico+" ya existe");
	}

	public void altaClienteParticular(String codigoUnico, String dni,
			String nombre, String apellido) throws Exception {

		if (obtenerCliente(codigoUnico) == null)
			clientes.add(new Particular(codigoUnico, dni, nombre, apellido));
		else
			throw new Exception("Cliente con codigo "+codigoUnico+" ya existe");
	}

	public void bajaCliente(String codigoUnico){
		for (Cliente c : clientes){
			if (c.getCodigoUnico().equals(codigoUnico)){
				clientes.remove(c);
				return;
			}
		}
	}
	
	public void asignarCuentaCorriente(String codigoUnico, Float montoActual, Float montoAutorizado) throws Exception{

		if(esClienteEmpresa(codigoUnico)){

			Empresa c = (Empresa) obtenerCliente(codigoUnico);
			c.setCuentaCorriente(new CuentaCorriente(montoActual, montoAutorizado));		
		}
		else
			throw new Exception("El cliente "+codigoUnico+" no es una empresa");
	}

	public boolean esClienteEmpresa(String codigoUnico){

		for(Cliente c : clientes)
			if(c.getCodigoUnico().equals(codigoUnico) && c.getClass().equals(Empresa.class))
				return true;
		return false;
	}
	
	public Cliente obtenerCliente(String codigoUnico) {
		for (Cliente c : clientes)
			if (c.getCodigoUnico().equals(codigoUnico))
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
