package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class GenericJFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;

	protected abstract void inicializar();

	protected abstract void configurar();

	public abstract void actionPerformed(ActionEvent e);

	protected void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	protected void mostrarInformacion(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
}
