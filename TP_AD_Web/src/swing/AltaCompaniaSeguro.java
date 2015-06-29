package swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rmi.delegate.BusinessDelegate;
import views.clientes.PagoView;

public class AltaCompaniaSeguro extends GenericJFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaCompaniaSeguro frame = new AltaCompaniaSeguro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaCompaniaSeguro() {
		inicializar();
		configurar();
	}

	protected void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Listar Pagos");
	}

	protected void configurar() {
		try {
			DefaultTableModel modelo = new DefaultTableModel();
			// COLUMNAS
			modelo.addColumn("Proveedor");
			modelo.addColumn("Monto");
			modelo.addColumn("Fecha");
			modelo.addColumn("Estado");
			// FILAS
			List<PagoView> pagos = BusinessDelegate.getInstance().getInterfaz().obtenerPagos();
			for (PagoView p : pagos) {
				String estado = p.isPagado() ? "Pagado" : "Pendiente";
				Object[] row = { p.getProveedor().getNombre(), p.getMonto(), p.getFecha(), estado };
				modelo.addRow(row);
			}
			JTable tabla = new JTable() {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
			};
			tabla.getTableHeader().setReorderingAllowed(false);
			tabla.setModel(modelo);
			tabla.setAutoCreateRowSorter(true);
			contentPane.add(new JScrollPane(tabla));
		} catch (Exception e) {
			mostrarError("Ocurrio un error mientras se obtenian los datos.");
		}
	}

	public void actionPerformed(ActionEvent e) {
	}
}
