package swing;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rmi.delegate.BusinessDelegate;
import views.clientes.PagoView;

public class ListarPagos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarPagos frame = new ListarPagos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListarPagos() {
		inicializar();
		configurar();
	}

	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Listar Pagos");
	}

	private void configurar() {
		DefaultTableModel modelo = new DefaultTableModel();
		// COLUMNAS
		modelo.addColumn("Proveedor");
		modelo.addColumn("Monto");
		modelo.addColumn("Fecha");
		modelo.addColumn("Estado");
		// FILAS
		try {
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
			dispose();
		}
	}

	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
