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
import views.misc.TamanoView;
import views.vehiculos.VehiculoExternoView;

public class AltaVehiculoExterno extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnAgregar;
	private JComboBox<String> comboTipo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaVehiculoExterno frame = new AltaVehiculoExterno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaVehiculoExterno() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Alta Vehiculo Externo");
	}

	protected void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));
		String[] labelStrings = { "ID Proveedor", "Patente", "Profundidad", "Alto", "Ancho", "Peso", "Tara", "Tarifa" };
		textFields = new JTextField[labelStrings.length];
		for (int i = 0; i < labelStrings.length; i++) {
			textFields[i] = new JTextField();
			panelCentro.add(new JLabel(labelStrings[i]));
			panelCentro.add(textFields[i]);
		}
		comboTipo = new JComboBox<String>();
		for (TipoVehiculo t : TipoVehiculo.values()) {
			comboTipo.addItem(t.toString());
		}
		panelCentro.add(new JLabel("Tipo"));
		panelCentro.add(comboTipo);
		contentPane.add(panelCentro, BorderLayout.CENTER);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			if (!hayCampoVacio()) {
				int i = 0;
				String idProveedor = textFields[i++].getText();
				String patente = textFields[i++].getText();
				String profundidad = textFields[i++].getText();
				String alto = textFields[i++].getText();
				String ancho = textFields[i++].getText();
				String peso = textFields[i++].getText();
				String tara = textFields[i++].getText();
				String tarifa = textFields[i++].getText();
				String tipo = comboTipo.getSelectedItem().toString();

				try {
					TamanoView t = new TamanoView(Float.parseFloat(profundidad), Float.parseFloat(alto), Float.parseFloat(ancho));
					VehiculoExternoView v = new VehiculoExternoView(patente, t, Float.parseFloat(peso), Float.parseFloat(tara),
							Float.parseFloat(tarifa), tipo);
					BusinessDelegate.getInstance().getInterfaz().altaVehiculoExterno(Integer.parseInt(idProveedor), v);
					mostrarInformacion("Vehiculo externo agregado correctamente.");
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
