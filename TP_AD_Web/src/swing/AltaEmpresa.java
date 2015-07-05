package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.delegate.BusinessDelegate;
import views.clientes.CuentaCorrienteView;
import views.clientes.EmpresaView;

public class AltaEmpresa extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JTextField[] textFields;
	private JButton btnAgregar;
	private JCheckBox chkEsRegular;
	private JCheckBox chkDepositoPrevio;

	public AltaEmpresa() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Alta Empresa");
	}

	protected void configurar() {
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 1));
		String[] labelStrings = { "Nombre", "Monto Autorizado" };
		textFields = new JTextField[labelStrings.length];
		for (int i = 0; i < labelStrings.length; i++) {
			textFields[i] = new JTextField();
			panelCentro.add(new JLabel(labelStrings[i]));
			panelCentro.add(textFields[i]);
		}
		chkEsRegular = new JCheckBox("Cliente Regular");
		panelCentro.add(chkEsRegular);
		chkDepositoPrevio = new JCheckBox("Utiliza Deposito Previo");
		panelCentro.add(chkDepositoPrevio);
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
				String montoAutorizado = textFields[i++].getText();
				boolean esRegular = chkEsRegular.isSelected();
				boolean depositoPrevio = chkDepositoPrevio.isSelected();
				try {
					CuentaCorrienteView ccv = new CuentaCorrienteView(depositoPrevio, Float.parseFloat(montoAutorizado), 0f);
					EmpresaView ev = new EmpresaView(nombre, esRegular, ccv);
					BusinessDelegate.getInstance().getInterfaz().altaEmpresa(ev);
					mostrarInformacion("Empresa agregada correctamente.");
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
