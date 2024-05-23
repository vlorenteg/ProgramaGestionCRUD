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

public class BajaEmpleado implements WindowListener, ActionListener {
    Frame frameBajaEmpleado = new Frame("Baja Empleado");
    Dialog dlgSeguro = new Dialog(frameBajaEmpleado, "¿Está seguro?", true);
    Dialog dlgMensaje = new Dialog(frameBajaEmpleado, "Mensaje", true);
    Label lblElegir = new Label("  -  Seleccione el Empleado que desea eliminar:  - ");
    Label lblSeguro = new Label("¿Está seguro de eliminar?");
    Label lblMensaje = new Label("Usuario Eliminado");
    Choice choEmpleados = new Choice();
    Button btnEliminar = new Button("Eliminar");
    Button btnSi = new Button("Sí");
    Button btnNo = new Button("No");
    Datos c = new Datos();
    
    BajaEmpleado(){
        c.rellenarChoiceEmpleados(choEmpleados);
        frameBajaEmpleado.setSize(350, 180);
        frameBajaEmpleado.setLayout(new FlowLayout());
        frameBajaEmpleado.addWindowListener(this);
        frameBajaEmpleado.add(lblElegir);
        frameBajaEmpleado.add(choEmpleados);
        btnEliminar.addActionListener(this);
        frameBajaEmpleado.add(btnEliminar);
        frameBajaEmpleado.setResizable(false);
        frameBajaEmpleado.setLocationRelativeTo(null);
        frameBajaEmpleado.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String tabla[] = choEmpleados.getSelectedItem().split("-");

        if (e.getSource().equals(btnEliminar)) {
            String tabla1[] = choEmpleados.getSelectedItem().split("-");
            if (choEmpleados.getSelectedIndex() != 0) {
                String idEmpleado = tabla1[0];
                c.bajaEmpleado(idEmpleado);
                dlgSeguro.setLayout(new FlowLayout());
                dlgSeguro.setSize(340,100);
                dlgSeguro.addWindowListener(this);
                lblSeguro.setText("¿Está seguro de eliminar el Empleado: " + tabla1[1] + "?");
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
            String idEmpleado = tabla[0];
            int respuesta = c.bajaEmpleado(idEmpleado);
            dlgMensaje.setLayout(new FlowLayout());
            dlgMensaje.setSize(170,100);
            dlgMensaje.addWindowListener(this);
            if(respuesta==0) {
                lblMensaje.setText("Empleado Eliminado");
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
            frameBajaEmpleado.setVisible(false);
        }
        else {
            frameBajaEmpleado.setVisible(false);
        }
    }

    public void windowOpened(WindowEvent e) {}

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}
}



