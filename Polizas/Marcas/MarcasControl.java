package polizas.Marcas;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {
    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
    }

    public void marcasEventos() {
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        mv.btn_a.addActionListener(e -> {
            Object marca = new Marca(0, mv.txt1.getText());
            JOptionPane.showMessageDialog(mv, mm.create(marca));
            mv.actualizarVista(mm.read());
        });

        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Registro: " + mv.marcasModelo.getValueAt(mv.tabla.getSelectedRow(), 0));
                mv.txt1.setText((String) mv.marcasModelo.getValueAt(mv.tabla.getSelectedRow(), 1));
            }
        });

        mv.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int fila = mv.tabla.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("Registro Fila: " + fila);
                }
            }
        });

        mv.cmbMarcas.addActionListener(e -> {
            Marca m = (Marca) mv.cmbMarcas.getSelectedItem();
        });
    }
}