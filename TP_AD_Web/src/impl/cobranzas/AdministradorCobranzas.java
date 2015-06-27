package impl.cobranzas;

import impl.clientes.CuentaCorriente;
import impl.clientes.Pago;

import java.util.ArrayList;
import java.util.List;

import persistence.CobranzasDAO;
import views.clientes.CuentaCorrienteView;
import views.clientes.PagoView;

public class AdministradorCobranzas {
	private static AdministradorCobranzas instance;
	private CobranzasDAO cobranzasDao;
	
	private AdministradorCobranzas() {
		cobranzasDao = CobranzasDAO.getInstance();
	}
	
	public static AdministradorCobranzas getInstance() {
		if (instance == null)
			instance = new AdministradorCobranzas();
		return instance;
	}
	
	public List<PagoView> obtenerPagos() {
		List<PagoView> pagosView = new ArrayList<PagoView>();
		for (Pago pago : cobranzasDao.obtenerPagos()) {
			pagosView.add(pago.getView());
		}
		return pagosView;
	}
	
	public List<CuentaCorrienteView> obtenerCuentasCorrientes() {
		List<CuentaCorrienteView> cuentasView = new ArrayList<CuentaCorrienteView>();
		for (CuentaCorriente cuenta : cobranzasDao.obtenerCuentasCorrientes()) {
			cuentasView.add(cuenta.getView());
		}
		return cuentasView;
	}
}
