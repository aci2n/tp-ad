package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.ControladorPrincipal;

public class ActualizarPrecioVehiculo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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

	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Actualizar Precio Vehiculo");
	}

	private void configurar() {
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
					ControladorPrincipal.getInstance().getAdministradorVehiculos().actualizarPrecioVehiculo(id, precioF);
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

	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarInformacion(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
}
