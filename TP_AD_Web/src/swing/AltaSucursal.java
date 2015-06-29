package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.delegate.BusinessDelegate;
import views.misc.CoordenadaView;
import views.misc.UbicacionView;
import views.sucursales.SucursalView;

public class AltaSucursal extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnAgregar;

	public AltaSucursal() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Alta Sucursal");
	}

	protected void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));
		String[] labelStrings = { "Nombre", "Pais", "Provincia", "Ciudad", "Calle", "Altura", "Piso", "Departamento", "Latitud", "Longitud" };
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
				String nombre = textFields[i++].getText();
				String pais = textFields[i++].getText();
				String provincia = textFields[i++].getText();
				String ciudad = textFields[i++].getText();
				String calle = textFields[i++].getText();
				String altura = textFields[i++].getText();
				String piso = textFields[i++].getText();
				String departamento = textFields[i++].getText();
				String latitud = textFields[i++].getText();
				String longitud = textFields[i++].getText();

				try {
					Float latitudFloat = Float.parseFloat(latitud);
					Float longitudFloat = Float.parseFloat(longitud);
					CoordenadaView c = new CoordenadaView(latitudFloat, longitudFloat);
					UbicacionView u = new UbicacionView(pais, provincia, ciudad, calle, altura, piso, departamento, c);
					SucursalView s = new SucursalView(nombre, u);
					BusinessDelegate.getInstance().getInterfaz().altaSucursal(s);
					mostrarInformacion("Sucursal agregada correctamente.");
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
