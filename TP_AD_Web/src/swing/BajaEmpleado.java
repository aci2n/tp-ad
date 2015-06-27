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

import rmi.delegate.BusinessDelegate;

public class BajaEmpleado extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField[] textFields;
	private JButton btnEliminar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaEmpleado frame = new BajaEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BajaEmpleado() {
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
		setTitle("Baja Empleado");
	}

	private void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));

		String[] labelStrings = { "ID Empleado" };
		textFields = new JTextField[labelStrings.length];

		for (int i = 0; i < labelStrings.length; i++) {
			textFields[i] = new JTextField();
			panelCentro.add(new JLabel(labelStrings[i]));
			panelCentro.add(textFields[i]);
		}

		contentPane.add(panelCentro, BorderLayout.CENTER);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			if (!hayCampoVacio()) {
				int i = 0;
				String idEmpleado = textFields[i++].getText();

				try {
					int id = Integer.parseInt(idEmpleado);
					BusinessDelegate.getInstance().getInterfaz().bajaEmpleado(id);
					mostrarInformacion("Empleado eliminado correctamente.");
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
