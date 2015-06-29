package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.delegate.BusinessDelegate;

public class ActualizarPrecioVehiculo extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnActualizar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualizarPrecioVehiculo frame = new ActualizarPrecioVehiculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ActualizarPrecioVehiculo() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Actualizar Precio Vehiculo");
	}

	protected void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));
		String[] labelStrings = { "ID Vehiculo", "Precio nuevo" };
		textFields = new JTextField[labelStrings.length];
		for (int i = 0; i < labelStrings.length; i++) {
			textFields[i] = new JTextField();
			panelCentro.add(new JLabel(labelStrings[i]));
			panelCentro.add(textFields[i]);
		}
		contentPane.add(panelCentro, BorderLayout.CENTER);
		btnActualizar = new JButton("Agregar");
		btnActualizar.addActionListener(this);
		contentPane.add(btnActualizar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			if (!hayCampoVacio()) {
				int i = 0;
				String idVehiculo = textFields[i++].getText();
				String precio = textFields[i++].getText();
				try {
					int id = Integer.parseInt(idVehiculo);
					float precioF = Float.parseFloat(precio);
					BusinessDelegate.getInstance().getInterfaz().actualizarPrecioVehiculo(id, precioF);
					mostrarInformacion("Vehiculo actualizado correctamente.");
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
