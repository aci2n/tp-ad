package swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.controllers.ControladorPrincipal;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class VentanaAltaVehiculo extends JFrame {

	//me olvide un par de campos pero me aburri despues los agrego
	private JPanel contentPane;
	private JTextField tfPatente;
	private JTextField tfPeso;
	private JTextField tfAncho;
	private JTextField tfAlto;
	private JTextField tfProfundidad;
	private JTextField tfTara;
	private final JTextField tfTarifa = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaVehiculo frame = new VentanaAltaVehiculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAltaVehiculo() {
		setTitle("Alta Vehiculo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblPatente = new JLabel("Patente");
		contentPane.add(lblPatente, "2, 2, right, default");

		tfPatente = new JTextField();
		contentPane.add(tfPatente, "4, 2, fill, default");
		tfPatente.setColumns(10);

		JLabel lblPeso = new JLabel("Peso");
		contentPane.add(lblPeso, "2, 4, right, default");

		tfPeso = new JTextField();
		contentPane.add(tfPeso, "4, 4, fill, top");
		tfPeso.setColumns(10);

		JLabel lblAncho = new JLabel("Ancho");
		contentPane.add(lblAncho, "2, 6, right, default");

		tfAncho = new JTextField();
		contentPane.add(tfAncho, "4, 6, fill, default");
		tfAncho.setColumns(10);

		JLabel lblAlto = new JLabel("Alto");
		contentPane.add(lblAlto, "2, 8, right, default");

		tfAlto = new JTextField();
		contentPane.add(tfAlto, "4, 8, fill, top");
		tfAlto.setColumns(10);

		JLabel lblProfundidad = new JLabel("Profundidad");
		contentPane.add(lblProfundidad, "2, 10, right, default");

		tfProfundidad = new JTextField();
		contentPane.add(tfProfundidad, "4, 10, fill, top");
		tfProfundidad.setColumns(10);

		JLabel lblTara = new JLabel("Tara");
		contentPane.add(lblTara, "2, 12, right, default");

		tfTara = new JTextField();
		contentPane.add(tfTara, "4, 12, fill, default");
		tfTara.setColumns(10);

		JLabel lblTarifa = new JLabel("Tarifa");
		contentPane.add(lblTarifa, "2, 14, right, default");
		tfTarifa.setText("");
		contentPane.add(tfTarifa, "4, 14");
		tfTarifa.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo");
		contentPane.add(lblTipo, "2, 16, right, default");

		JComboBox cbTipo = new JComboBox();
		contentPane.add(cbTipo, "4, 16, fill, default");

		JLabel lblProveedor = new JLabel("Proveedor");
		contentPane.add(lblProveedor, "2, 18, right, default");

		JComboBox cbProveedores = new JComboBox();
		contentPane.add(cbProveedores, "4, 18, fill, default");

		JButton btnAgregar = new JButton("Agregar");
		contentPane.add(btnAgregar, "4, 20, right, default");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbProveedores.getSelectedItem().toString().equals("Local")) {
					//da de alta vehiculo local
				} else {
					//da de alta vehiculo externo
				}
			}
		});
	}

}
