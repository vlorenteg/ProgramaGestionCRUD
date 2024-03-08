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

public class Login implements WindowListener, ActionListener
{

	Frame login = new Frame("Login");
	Label lblNombre = new Label("Usuario:");
	Label lblClave = new Label("Clave:");
	TextField nombreUsuario = new TextField(25);
	TextField claveUsuario = new TextField (25);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Datos c = new Datos();
	int tipoUsuario;
	Dialog mensajeError = new Dialog (login, "Error", true);
	Label lblMensaje = new Label("Las credenciales son incorrectas");
	Button btnVolver = new Button("Volver");
	
		
	Login()
	{
		login.setLayout(new FlowLayout());
		login.setSize(260,250);
		login.setResizable(false);
		login.add(lblNombre);
		login.add(nombreUsuario);
		login.add(lblClave);
		claveUsuario.setEchoChar('*');
		login.add(claveUsuario);
		login.add(btnAceptar);
		login.add(btnCancelar);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		login.addWindowListener((WindowListener) this);
		login.setVisible(true);
		
	}
	public static void main(String[] args)
	{
		new Login();

	}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		if(mensajeError.isActive()) {
			btnVolver.removeActionListener(this);
			mensajeError.removeWindowListener(this);
			mensajeError.setVisible(false);
		}
		else {
			System.exit(0);
		}	
	}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnAceptar))
				{
					String usuario = nombreUsuario.getText();
					String clave = claveUsuario.getText();
					tipoUsuario = c.comprobarCredenciales(usuario, clave);
					c.tipoUsuario = tipoUsuario;
					if(tipoUsuario!=-1)
					{
						new MenuPrincipal(usuario, tipoUsuario);
						login.setVisible(false);				
					}

					else
					{
						mensajeError.setLayout(new FlowLayout());
						mensajeError.addWindowListener(this);
						mensajeError.add(lblMensaje);
						mensajeError.setSize(260, 80);
						mensajeError.setResizable(false);
						mensajeError.setLocationRelativeTo(null);
						mensajeError.setVisible(true);
					}
				}
			}
}
	
	
	
	
	