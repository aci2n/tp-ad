package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener {

	private JMenuItem miActualizarPrecioVehiculo, miAgregarParadaIntermedia, miAgregarSeguro, miAgregarTarea, miAltaCompaniaSeguro, miAltaEmpleado,
			miAltaProveedor, miAltaSucursal, miAltaVehiculoExterno, miBajaEmpleado, miListarCompaniasSeguro, miListarPagos, miRealizarCobroParcial,
			miRealizarPago, miAltaVehiculoLocal;

	public Menu() {
		componentes();
		comportamiento();
	}

	private void componentes() {

		setLayout(new BorderLayout());
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);

		// Vehiculos
		JMenu mVehiculos = addMenu(bar, "Vehiculos");
		addMenuItem(mVehiculos, miAltaVehiculoExterno, "Alta Vehiculo Externo");
		addMenuItem(mVehiculos, miActualizarPrecioVehiculo, "Actualizar Precio Vehiculo");
		addMenuItem(mVehiculos, miAgregarTarea, "Agregar Tarea");
		addMenuItem(mVehiculos, miAltaVehiculoLocal, "Alta Vehiculo Local");

		JMenu mViajes = addMenu(bar, "Viajes");
		addMenuItem(mViajes, miAgregarParadaIntermedia, "Agregar Parada Intermedia");

		JMenu mSeguros = addMenu(bar, "Seguros");
		addMenuItem(mSeguros, miAltaCompaniaSeguro, "Alta Compania Seguro");
		addMenuItem(mSeguros, miAgregarSeguro, "Agregar Seguro");
		addMenuItem(mSeguros, miListarCompaniasSeguro, "Listar Companias Seguro");

		JMenu mSucursales = addMenu(bar, "Sucursales");
		addMenuItem(mSucursales, miAltaSucursal, "Alta Sucursal");
		addMenuItem(mSucursales, miAltaEmpleado, "Alta Empleado");
		addMenuItem(mSucursales, miBajaEmpleado, "Baja Empleado");

		JMenu mPagos = addMenu(bar, "Pagos");
		addMenuItem(mPagos, miListarPagos, "Listar Pagos");
		addMenuItem(mPagos, miRealizarPago, "Realizar Pago");

		JMenu mFacturas = addMenu(bar, "Facturas");
		addMenuItem(mFacturas, miRealizarCobroParcial, "Realizar Cobro Parcial");

		JMenu mProveedores = addMenu(bar, "Proveedores");
		addMenuItem(mProveedores, miAltaProveedor, "Alta Proveedor");
	}

	private JMenu addMenu(JMenuBar bar, String title) {
		JMenu menu = new JMenu(title);
		bar.add(menu);
		return menu;
	}

	private void addMenuItem(JMenu menu, JMenuItem item, String title) {
		item = new JMenuItem(title);
		menu.add(item);
		item.addActionListener(this);
	}

	private void comportamiento() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Sistema de Administracion de Cargas");
		setSize(500, 150);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		JFrame frame = null;
		switch (e.getActionCommand()) {
		case "Alta Vehiculo Externo":
			frame = new AltaVehiculoExterno();
			break;
		case "Actualizar Precio Vehiculo":
			frame = new ActualizarPrecioVehiculo();
			break;
		case "Agregar Seguro":
			frame = new AgregarSeguro();
			break;
		case "Agregar Tarea":
			frame = new AgregarTarea();
			break;
		case "Alta Compania Seguro":
			frame = new AltaCompaniaSeguro();
			break;
		case "Alta Empleado":
			frame = new AltaEmpleado();
			break;
		case "Alta Proveedor":
			frame = new AltaProveedor();
			break;
		case "Alta Sucursal":
			frame = new AltaSucursal();
			break;
		case "Baja Empleado":
			frame = new BajaEmpleado();
			break;
		case "Listar Companias Seguro":
			frame = new ListarCompaniasSeguro();
			break;
		case "Listar Pagos":
			frame = new ListarPagos();
			break;
		case "Realizar Cobro Parcial":
			frame = new RealizarCobroParcial();
			break;
		case "Realizar Pago":
			frame = new RealizarPago();
			break;
		case "Agregar Parada Intermedia":
			frame = new AgregarParadaIntermedia();
			break;
		case "Alta Vehiculo Local":
			frame = new AltaVehiculoLocal();
			break;
		}
		if (frame != null) {
			frame.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new Menu();
	}

}