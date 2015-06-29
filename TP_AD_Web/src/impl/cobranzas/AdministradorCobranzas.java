package impl.cobranzas;

import impl.cargas.Carga;
import impl.clientes.CuentaCorriente;
import impl.vehiculos.Proveedor;
import impl.vehiculos.VehiculoExterno;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import persistence.CobranzasDAO;
import persistence.FacturaDAO;
import persistence.PagoDAO;
import views.clientes.CuentaCorrienteView;
import views.clientes.PagoView;

public class AdministradorCobranzas {
	private static AdministradorCobranzas instance;
	private CobranzasDAO cobranzasDao;
	private FacturaDAO facturaDao;
	private PagoDAO pagoDao;

	private AdministradorCobranzas() {
		cobranzasDao = CobranzasDAO.getInstance();
		facturaDao = FacturaDAO.getInstance();
		pagoDao = PagoDAO.getInstance();
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

	public void generarPago(Viaje viaje) {
		Proveedor proveedor = ((VehiculoExterno) viaje.getVehiculo()).getProveedor();
		Float monto = viaje.getCosto();
		Date fecha = new Date();
		new Pago(proveedor, monto, fecha);
	}

	public void generarFactura(Carga c) {
		String tipoFactura = "Tipo A";
		Date fechaCreacion = new Date();
		Float montoTotal = c.calcularCosto();
		new Factura(tipoFactura, fechaCreacion, montoTotal, c);
	}

	public void realizarCobroParcial(Integer idFactura, Float monto) throws Exception {
		Factura factura = facturaDao.get(idFactura);
		if (factura != null) {
			factura.realizarCobroParcial(new Date(), monto);
		} else {
			throw new Exception("No existe factura con el ID ingresado.");
		}
	}

	public void realizarPago(Integer idPago) throws Exception {
		Pago pago = pagoDao.get(idPago);
		if (pago != null) {
			pago.pagar();
		} else {
			throw new Exception("No existe el pago ingresado.");
		}
	}
}
