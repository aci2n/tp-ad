package swing;

import impl.vehiculos.TipoVehiculo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.delegate.BusinessDelegate;
import util.Utilities;
import views.misc.TamanoView;
import views.personal.EmpleadoView;
import views.vehiculos.PlanMantenimientoView;
import views.vehiculos.VehiculoLocalView;

public class AltaVehiculoLocal extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnAgregar;
	private JComboBox<String> comboTipos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaVehiculoLocal frame = new AltaVehiculoLocal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaVehiculoLocal() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Alta Vehiculo");
	}

	protected void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));
		String[] labelStrings = { "ID de sucursal", "Patente", "Profundidad", "Alto", "Ancho", "Peso", "Tara", "Tarifa",
				"Vencimiento garantia (dd/MM/yyyy)", "Tipo mantenimiento", "Punto de control (km)", "Intervalo", "ID de Chofer"};
		textFields = new JTextField[labelStrings.length];
		for (int i = 0; i < labelStrings.length; i++) {
			textFields[i] = new JTextField();
			panelCentro.add(new JLabel(labelStrings[i]));
			panelCentro.add(textFields[i]);
		}
		comboTipos = new JComboBox<String>();
		comboTipos.addItem("kilometraje");
		comboTipos.addItem("kilometrajeRelativo");
		comboTipos.addItem("temporal");
		panelCentro.add(new JLabel("Tipo"));
		panelCentro.add(comboTipos);
		contentPane.add(panelCentro, BorderLayout.CENTER);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			if (!hayCampoVacio()) {
				int i = 0;
				String idSucursal = textFields[i++].getText();
				String patente = textFields[i++].getText();
				String profundidad = textFields[i++].getText();
				String alto = textFields[i++].getText();
				String ancho = textFields[i++].getText();
				String peso = textFields[i++].getText();
				String tara = textFields[i++].getText();
				String tarifa = textFields[i++].getText();
				String vencimiento = textFields[i++].getText();
				String tipoMantenimiento = textFields[i++].getText();
				String puntoControl = textFields[i++].getText();
				String intervalo = textFields[i++].getText();
				String tipo = comboTipos.getSelectedItem().toString();
				String idChofer = textFields[i++].getText();
				try {
					Integer idSuc = Integer.parseInt(idSucursal);
					Integer idChof = Integer.parseInt(idChofer);
					TamanoView tamano = new TamanoView(Float.parseFloat(alto), Float.parseFloat(ancho), Float.parseFloat(profundidad));
					VehiculoLocalView vehiculo = new VehiculoLocalView(
							patente,
							tamano,
							Float.parseFloat(peso),
							Float.parseFloat(tara),
							Float.parseFloat(tarifa),
							tipo,
							vencimiento,
							null
					);
					PlanMantenimientoView plan = new PlanMantenimientoView(tipoMantenimiento);
					plan.setIntervaloMantenimiento(Integer.parseInt(intervalo));
					plan.setPuntoControl(Float.parseFloat(puntoControl));

					BusinessDelegate.getInstance().getInterfaz().altaVehiculoLocal(idSuc, vehiculo, plan, idChof);
					mostrarInformacion("Vehiculo agregado correctamente.");		
				} catch (Exception ex) {
					mostrarError(ex.getMessage());
				}
			} else {
				mostrarError("Hay campos vacios.");
			}
		}
	}

	private boolean hayCampoVacio() {
		for (JTextField t : textFields)
			if (t.getText().equals(""))
				return true;
		return false;
	}
}
