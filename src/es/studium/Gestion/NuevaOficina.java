package es.studium.Gestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class NuevaOficina implements WindowListener, ActionListener{
		Frame NuevaOficina = new Frame("Nueva Oficina");
		Label lblNombre = new Label(" ------------ Nueva Oficina ------------ ");
		Dialog dlgMensaje = new Dialog(NuevaOficina, "Mensaje", true);
		Label lblDireccion = new Label("Dirección:");
		Label lblTelefono = new Label("Teléfono:");
		Label lblMensaje = new Label("Alta de Usuario Correcta");
		TextField direccionOficina = new TextField(15);
		TextField telefonoOficina = new TextField (15);
		Button btnAceptar = new Button("Aceptar");
		Button btnCancelar = new Button("Cancelar");
		Button btnVolver = new Button("Volver");
		Datos c = new Datos();
		
		NuevaOficina()
		{
			NuevaOficina.setLayout(new FlowLayout());
			NuevaOficina.setSize(260,250);
			NuevaOficina.setResizable(false);
			NuevaOficina.add(lblNombre);
			NuevaOficina.add(lblDireccion);
			NuevaOficina.add(direccionOficina);
			NuevaOficina.add(lblTelefono);
			NuevaOficina.add(telefonoOficina);
			NuevaOficina.add(btnAceptar);
			NuevaOficina.add(btnCancelar);
			btnAceptar.addActionListener(this);
			btnCancelar.addActionListener(this);
			NuevaOficina.addWindowListener((WindowListener) this);
			NuevaOficina.setLocationRelativeTo(null);
			NuevaOficina.setVisible(true);
		}

			
		
		@Override
		public void windowOpened(WindowEvent e){}
		@Override
		public void windowClosing(WindowEvent e){
			if(dlgMensaje.isActive())
			{
				dlgMensaje.setVisible(false);
			}
			else
			{
				NuevaOficina.setVisible(false);
			}
		}
		@Override
		public void windowClosed(WindowEvent e){}
		@Override
		public void windowIconified(WindowEvent e){}
		@Override
		public void windowDeiconified(WindowEvent e){}
		@Override
		public void windowActivated(WindowEvent e){}
		@Override
		public void windowDeactivated(WindowEvent e){}
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource().equals(btnAceptar)){
			    dlgMensaje.setSize(260, 250);
			    dlgMensaje.setLayout(new FlowLayout());
			    dlgMensaje.addWindowListener(this);

			    if(direccionOficina.getText().length()==0 || telefonoOficina.getText().length()==0)
			    {
			        lblMensaje.setText("Faltan campos por rellenar");
			    }
			    else
			    {
			        String sentencia = "insert into oficinas values (null, '" + direccionOficina.getText() + "', '" + telefonoOficina.getText() + "');";
			        int respuesta = c.NuevaOficina(sentencia);
			        
			        if(respuesta != 0)
			        {
			            lblMensaje.setText("Error al agregar la oficina");
			        }
			        else
			        {
			            lblMensaje.setText("Oficina correcta");
			        }
			    }

			    dlgMensaje.add(lblMensaje);
			    dlgMensaje.setResizable(false);
			    dlgMensaje.setLocationRelativeTo(null);
			    dlgMensaje.setVisible(true);
			}

			else if(e.getSource().equals(btnCancelar))
			{
				direccionOficina.setText("");
				telefonoOficina.setText("");
				direccionOficina.requestFocus();
				telefonoOficina.requestFocus();
				
				NuevaOficina.setVisible(false);
			}
		}
}

