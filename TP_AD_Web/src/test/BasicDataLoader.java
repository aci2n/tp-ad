package test;

import persistence.FacturaDAO;
import persistence.SeguimientoCargasDAO;

public class BasicDataLoader {
	public static void main(String[] args) throws Exception {
		// System.out.println(ControladorPrincipal.getInstance().fechaProbableLlegada(8));
		// for (int i = 0; i < 50; i++)
		FacturaDAO.getInstance().delete(FacturaDAO.getInstance().getFacturaDesdeCarga(1));
		SeguimientoCargasDAO.getInstance().deleteByCarga(1);
	}
}
