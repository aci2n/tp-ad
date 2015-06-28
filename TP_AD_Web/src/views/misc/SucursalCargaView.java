package views.misc;

import views.cargas.CargaView;
import views.sucursales.SucursalView;

public class SucursalCargaView {
	SucursalView sucursal;
	CargaView carga;

	public SucursalCargaView(SucursalView sucursal, CargaView carga) {
		this.sucursal = sucursal;
		this.carga = carga;
	}

	public SucursalView getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalView sucursal) {
		this.sucursal = sucursal;
	}

	public CargaView getCarga() {
		return carga;
	}

	public void setCarga(CargaView carga) {
		this.carga = carga;
	}
}
