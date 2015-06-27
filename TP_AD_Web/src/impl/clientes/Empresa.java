package impl.clientes;

import impl.productos.Producto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import persistence.ClienteDAO;
import views.clientes.EmpresaView;
import views.productos.ProductoView;

@Entity
@Table(name = "Clientes_Empresas")
public class Empresa extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3035063526042004506L;

	@Column(name = "regular")
	private boolean regular;
	@Embedded
	private CuentaCorriente cuentaCorriente;
	@ManyToMany
	@JoinTable(name = "Empresas_Productos", joinColumns = @JoinColumn(name = "id_empresa"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
	private Collection<Producto> productos;

	public Empresa(String nombre, CuentaCorriente cuentaCorriente) {
		this.nombre = nombre;
		this.cuentaCorriente = cuentaCorriente;
		this.id = ClienteDAO.getInstance().insert(this);
	}

	public Empresa() {

	}

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}

	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public Collection<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public void cobrarEnvio(Factura factura) {
		// TODO Auto-generated method stub

	}

	public void agregarProducto(Producto producto) {
		if (productos == null)
			productos = new ArrayList<Producto>();
		productos.add(producto);
	}

	public EmpresaView getView() {
		// TODO
		EmpresaView view = new EmpresaView(nombre, Boolean.toString(regular));
		List<ProductoView> productos = null;

		try {
			productos = new ArrayList<ProductoView>();
			for (Producto p : getProductos())
				productos.add(p.getView());

			//view.setCuentaCorriente(cuentaCorriente.getView());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
}
