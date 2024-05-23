package es.studium.Gestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaCliente implements WindowListener, ActionListener {
    Frame frameBajaCliente = new Frame("Baja Cliente");
    Dialog dlgSeguro = new Dialog(frameBajaCliente, "¿Está seguro?", true);
    Dialog dlgMensaje = new Dialog(frameBajaCliente, "Mensaje", true);
    Label lblElegir = new Label("  -  Seleccione el Cliente que desea eliminar:  - ");
    Label lblSeguro = new Label("¿Está seguro de eliminar?");
    Label lblMensaje = new Label("Cliente Eliminado");
    Choice choClientes = new Choice();
    Button btnEliminar = new Button("Eliminar");
    Button btnSi = new Button("Sí");
    Button btnNo = new Button("No");
    Datos c = new Datos();
    
    BajaCliente(){
        c.rellenarChoiceClientes(choClientes);
        frameBajaCliente.setSize(300, 250);
        frameBajaCliente.setLayout(new FlowLayout());
        frameBajaCliente.addWindowListener(this);
        frameBajaCliente.add(lblElegir);
        frameBajaCliente.add(choClientes);
        btnEliminar.addActionListener(this);
        frameBajaCliente.add(btnEliminar);
        frameBajaCliente.setResizable(false);
        frameBajaCliente.setLocationRelativeTo(null);
        frameBajaCliente.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String tabla[] = choClientes.getSelectedItem().split("-");

        if (e.getSource().equals(btnEliminar)) {
            String tabla1[] = choClientes.getSelectedItem().split("-");
            if (choClientes.getSelectedIndex() != 0) {
                String idCliente = tabla1[0];
                c.bajaCliente(idCliente);
                dlgSeguro.setLayout(new FlowLayout());
                dlgSeguro.setSize(340,100);
                dlgSeguro.addWindowListener(this);
                lblSeguro.setText("¿Está seguro de eliminar el Cliente: " + tabla1[1] + "?");
                dlgSeguro.add(lblSeguro);
                btnSi.addActionListener(this);
                dlgSeguro.add(btnSi);
                btnNo.addActionListener(this);
                dlgSeguro.add(btnNo);
                dlgSeguro.setResizable(false);
                dlgSeguro.setLocationRelativeTo(null);
                dlgSeguro.setVisible(true);
            }
        }
        else if (e.getSource().equals(btnNo)) {
            dlgSeguro.setVisible(false);
        }
        else if (e.getSource().equals(btnSi)) {
            String idCliente = tabla[0];
            int respuesta = c.bajaCliente(idCliente);
            dlgMensaje.setLayout(new FlowLayout());
            dlgMensaje.setSize(170,100);
            dlgMensaje.addWindowListener(this);
            if(respuesta==0) {
                lblMensaje.setText("Cliente Eliminado");
            }
            else {
                lblMensaje.setText("Error al eliminar");
            }
            dlgMensaje.add(lblMensaje);
            dlgMensaje.setResizable(false);
            dlgMensaje.setLocationRelativeTo(null);
            dlgMensaje.setVisible(true);
        }
        
    }
    
    public void windowClosing(WindowEvent e) {
        if(dlgSeguro.isActive()) {
            dlgSeguro.setVisible(false);
        }
        else if(dlgMensaje.isActive()) {
            dlgMensaje.setVisible(false);
            dlgSeguro.setVisible(false);
            frameBajaCliente.setVisible(false);
        }
        else {
            frameBajaCliente.setVisible(false);
        }
    }

    public void windowOpened(WindowEvent e) {}

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}
}

