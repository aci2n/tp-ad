package rmi.clientes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import rmi.delegate.BusinessDelegate;
import views.viajes.CompaniaSeguroView;
import views.viajes.SeguroView;

public class ListarCompaniasSeguro extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<CompaniaSeguroView> comboCompanias;
	private JLabel lblCompania;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarCompaniasSeguro frame = new ListarCompaniasSeguro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListarCompaniasSeguro() {
		inicializar();
		configurar();
	}

	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Listar Companias Seguro");
	}

	private void configurar() {
		contentPane.setLayout(new BorderLayout());

		comboCompanias = new JComboBox<CompaniaSeguroView>();
		List<CompaniaSeguroView> companias = new ArrayList<CompaniaSeguroView>();
		try {
			companias = BusinessDelegate.getInstance().getInterfaz().getCompaniasSeguroView();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < companias.size(); i++) {
			comboCompanias.addItem(companias.get(i));
		}

		comboCompanias.setSelectedIndex(-1);
		comboCompanias.addActionListener(this);

		contentPane.add(comboCompanias, BorderLayout.NORTH);

		lblCompania = new JLabel();
		contentPane.add(new JScrollPane(lblCompania), BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboCompanias) {
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			for (SeguroView s : ((CompaniaSeguroView) comboCompanias.getSelectedItem()).getSegurosView()) {
				sb.append("Seguro: " + s.getNombre());
				sb.append("<br>");
				sb.append("Tarifa: " + s.getTarifa());
				sb.append("<br>");
				sb.append("Tipo carga: " + s.getTipoCarga());
				sb.append("<br><br>");
			}
			sb.append("</html>");
			lblCompania.setText(sb.toString());
		}
	}
}
