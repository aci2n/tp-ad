package swing;

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
import views.personal.EmpleadoView;

public class AltaEmpleado extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnAgregar;
	private JComboBox<String> comboPuestos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaEmpleado frame = new AltaEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaEmpleado() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Alta Empleado");
	}

	protected void configurar() {
		try {
			JPanel panelCentro = new JPanel();
			panelCentro.setLayout(new GridLayout(0, 1));
			String[] labelStrings = { "ID Sucursal", "CUIT", "DNI", "Nombre", "Apellido", "Fecha Nacimiento" };
			textFields = new JTextField[labelStrings.length];
			for (int i = 0; i < labelStrings.length; i++) {
				textFields[i] = new JTextField();
				panelCentro.add(new JLabel(labelStrings[i]));
				panelCentro.add(textFields[i]);
			}
			comboPuestos = new JComboBox<String>();
			for (String s : BusinessDelegate.getInstance().getInterfaz().getValuesTipoPuesto())
				comboPuestos.addItem(s);
			panelCentro.add(new JLabel("Puesto"));
			panelCentro.add(comboPuestos);
			contentPane.add(panelCentro, BorderLayout.CENTER);
			btnAgregar = new JButton("Agregar");
			btnAgregar.addActionListener(this);
			contentPane.add(btnAgregar, BorderLayout.SOUTH);
		} catch (Exception e) {
			mostrarError("Ocurrio un error mientras se obtenian los datos.");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			if (!hayCampoVacio()) {
				int i = 0;
				String idSucursal = textFields[i++].getText();
				String cuit = textFields[i++].getText();
				String dni = textFields[i++].getText();
				String nombre = textFields[i++].getText();
				String apellido = textFields[i++].getText();
				String fechaNacimiento = textFields[i++].getText();
				String tipo = comboPuestos.getSelectedItem().toString();
				try {
					int id = Integer.parseInt(idSucursal);
					EmpleadoView empleado = new EmpleadoView(cuit, dni, nombre, apellido, fechaNacimiento, tipo);
					BusinessDelegate.getInstance().getInterfaz().agregarEmpleadoASucursal(id, empleado);
					mostrarInformacion("Empleado agregado correctamente.");
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
