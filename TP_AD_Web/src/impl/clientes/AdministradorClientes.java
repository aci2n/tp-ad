package impl.clientes;

import java.util.ArrayList;
import java.util.List;

import persistence.ClienteDAO;
import views.clientes.CuentaCorrienteView;
import views.clientes.EmpresaView;
import views.clientes.ParticularView;
import views.clientes.ReceptorView;

public class AdministradorClientes {
	private static AdministradorClientes instance;
	private ClienteDAO clienteDao;

	private AdministradorClientes() {
		this.clienteDao = ClienteDAO.getInstance();
	}

	public static AdministradorClientes getInstance() {
		if (instance == null)
			instance = new AdministradorClientes();
		return instance;
	}

	public Integer altaClienteEmpresa(EmpresaView view) {
		CuentaCorriente c = null;
		if (view.getCuentaCorriente() != null) {
			c = new CuentaCorriente(view.getCuentaCorriente().isDepositoPrevio(),
					view.getCuentaCorriente().getMontoActual(),
					view.getCuentaCorriente().getMontoAutorizado());
		}
		Empresa e = new Empresa(view.getNombre(), view.isRegular(), c);
		return e.getId();
	}

	public Integer altaClienteParticular(ParticularView view) {
		Particular p = new Particular(view.getDni(), view.getNombre(), view.getApellido());
		return p.getId();
	}

	public Integer agregarReceptor(Integer clienteId, ReceptorView r) throws Exception {
		Particular particular = ClienteDAO.getInstance().obtenerClienteParticular(clienteId);
		if (particular != null) {
			return particular.agregarReceptor(r);
		} else {
			throw new Exception("El cliente particular ingresado no existe.");
		}
	}

	public void bajaCliente(Integer id) {
		//	TODO
	}

	public void asignarCuentaCorriente(EmpresaView eView, CuentaCorrienteView cView) throws Exception {
		Cliente cliente = clienteDao.get(eView.getId());

		if (cliente != null) {
			Empresa e = (Empresa) cliente;
			e.setCuentaCorriente(new CuentaCorriente(cView.isDepositoPrevio(), cView.getMontoActual(), cView.getMontoAutorizado()));
		} else {
			throw new Exception("El cliente con id" + eView.getId() + " no es una empresa");
		}
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

	public Cliente obtenerCliente(Integer id) {
		return clienteDao.get(id);
	}

}
