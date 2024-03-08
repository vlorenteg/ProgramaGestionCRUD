package es.studium.Gestion;

import java.awt.Button;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class MenuPrincipal implements WindowListener, ActionListener{
	Frame MenuPrincipal = new Frame("MenuPrincipal");
	MenuBar menuBar = new MenuBar();
	Menu menuOficinas = new Menu ("Oficinas");
    MenuItem nuevaOficina = new MenuItem("Alta");
    MenuItem consultaOficinas = new MenuItem("Consulta");
    MenuItem modificacionOficina = new MenuItem("Modificación");
    MenuItem bajaOficina = new MenuItem("Baja");
	Datos c = new Datos();
	static String nusuario;
	int tipoUsuario;
	Label lblNombre = new Label ("Oficinas");
	Button btnAlta = new Button("Alta");
	Button btnConsulta = new Button("Consulta");
	Button btnModificacion = new Button("Modificacion");
	Button btnBaja = new Button("Baja");
	

	MenuPrincipal(String usuario, int t){
		nusuario = usuario;
		tipoUsuario = t;
		MenuPrincipal.setLayout(new GridLayout(6, 1));
		MenuPrincipal.setSize(260,250);
		nuevaOficina.addActionListener(this);
		consultaOficinas.addActionListener(this);
		modificacionOficina.addActionListener(this);
		bajaOficina.addActionListener(this);
	       if (tipoUsuario == 0) {
	            menuOficinas.add(nuevaOficina);
	        } else if (tipoUsuario == 1) {
	            menuOficinas.add(nuevaOficina);
	            menuOficinas.add(consultaOficinas);
	            menuOficinas.add(modificacionOficina);
	            menuOficinas.add(bajaOficina);
	        }
        nuevaOficina.addActionListener(this);
        consultaOficinas.addActionListener(this);
        modificacionOficina.addActionListener(this);
        bajaOficina.addActionListener(this);
		MenuPrincipal.setResizable(false);
		MenuPrincipal.add(lblNombre);
		MenuPrincipal.add(btnAlta);
		MenuPrincipal.add(btnConsulta);
		MenuPrincipal.add(btnModificacion);
		MenuPrincipal.add(btnBaja);
		btnAlta.addActionListener(this);
		btnConsulta.addActionListener(this);
		btnModificacion.addActionListener(this);
		btnBaja.addActionListener(this);
		MenuPrincipal.addWindowListener((WindowListener) this);
		MenuPrincipal.setVisible(true);	
	}


	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		String salida = "Sale usuario '" + nusuario + "'";
		c.apunteLog(nusuario, salida);
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void actionPerformed(ActionEvent e){
		 if(e.getSource().equals(btnAlta)) {
			 new NuevaOficina();
		 }
		 else if(e.getSource().equals(btnConsulta)) {
			 new ConsultaOficinas();
		 }
		 else if(e.getSource().equals(btnModificacion)) {
			 new Modificacion();
		 }
		 else if(e.getSource().equals(btnBaja)) {
			 new Baja();
		 }
	            }
	        
	}
    
