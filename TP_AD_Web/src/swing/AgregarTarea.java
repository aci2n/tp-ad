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
import views.vehiculos.TareaView;

public class AgregarTarea extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnAgregar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarTarea frame = new AgregarTarea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AgregarTarea() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Agregar Tarea");
	}

	protected void configurar() {
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
}
