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
import views.vehiculos.TareaView;

public class VentanaGenerica extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField[] textFields;
	private JButton btnAgregar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGenerica frame = new VentanaGenerica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaGenerica() {
		inicializar();
		configurar();
	}

	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Agregar Tarea");
	}

	private void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));

		String[] labelStrings = { "ID Vehiculo", "Kilometraje", "Fecha entrega", "Fecha devolucion" };
		textFields = new JTextField[labelStrings.length];

		for (int i = 0; i < labelStrings.length; i++) {
			textFields[i] = new JTextField();
			panelCentro.add(new JLabel(labelStrings[i]));
			panelCentro.add(textFields[i]);
		}

		contentPane.add(panelCentro, BorderLayout.CENTER);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			if (!hayCampoVacio()) {
				int i = 0;
				String idVehiculo = textFields[i++].getText();
				String kilometraje = textFields[i++].getText();
				String fechaEntrega = textFields[i++].getText();
				String fechaDevolucion = textFields[i++].getText();

				try {
					int id = Integer.parseInt(idVehiculo);
					float kilometrajeF = Float.parseFloat(kilometraje);
					TareaView tarea = new TareaView(kilometrajeF, fechaEntrega, fechaDevolucion);
					BusinessDelegate.getInstance().getInterfaz().agregarTarea(id, tarea);
					mostrarInformacion("Tarea agregada correctamente.");
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
