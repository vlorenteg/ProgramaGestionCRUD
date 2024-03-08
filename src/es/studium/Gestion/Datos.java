package es.studium.Gestion;

import java.awt.Choice;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/programaGestion";
	String login = "root";
	String password = "Studium2023;";
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	public int tipoUsuario;
	
	Datos(){
		connection = this.conectar();
	}
	
	public Connection conectar()
    {
        try
        {
            Class.forName(driver);
            return(DriverManager.getConnection(url, login, password));
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Error 1-" + cnfe.getMessage());
        }
        catch (SQLException sqle)
        {
            System.out.println("Error 2-" + sqle.getMessage());
        }
        return null;
    }
	
	public int comprobarCredenciales(String u, String c)
	{
		String cadena = "select * from usuarios where nombreUsuario = '" + u + "' and claveUsuario = SHA2('" + c + "',256);";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(cadena);
			if(rs.next())
			{
				String entrada = "Accede usuario '" + u + "'";
				apunteLog(u, entrada);
				return rs.getInt("tipoUsuario");
			}
			else
			{
				return -1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 3-" + sqle.getMessage());
		}
		return -1;
	}


	public int dameTipoUsuario()
	{
		int tipo = 0;
		if(tipo==1) {
			return 1;
		}
		else {
		return 0;
		}
	}
	
	public int NuevaOficina(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-" + sqle.getMessage());
			return 1;
		}
	}

	public void apunteLog(String nusuario, String salida){}
	
	public void consultaOficina(TextArea txaConsulta)
	{
		String sentencia = "select idOficina, direccionOficina, telefonoOficina from oficinas order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Consulta Oficinas";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaConsulta.append(resultado.getString("idOficina")+ " - ");
				txaConsulta.append(resultado.getString("direccionOficina") + " - ");
				txaConsulta.append(resultado.getString("telefonoOficina") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-" + sqle.getMessage());
		} 
	}

	public void rellenarModificacionOficinas(Choice choOficinas)
	{
		String sentencia = "select idOficina, direccionOficina, telefonoOficina from oficinas order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choOficinas.add("Elige una Oficina...");
			while (resultado.next())
			{
				choOficinas.add(resultado.getString("idOficina") + "-" + resultado.getString("direccionOficina") + "-" + resultado.getString("telefonoOficina"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-" + sqle.getMessage());
		}
	}

	public String getOficinaEditar(String idOficina)
    {
        String resultado = "";
        String sentencia = "select * from oficinas where idOficina = " + idOficina;
        try
        {

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sentencia);
            resultSet.next();
            resultado =(resultSet.getString("idOficina") + "-" + resultSet.getString("direccionOficina") + "-" + resultSet.getString("telefonoOficina"));
        }
        catch (SQLException sqle)
        {
            System.out.println("Error 16-"+sqle.getMessage());
        }
        return resultado;
    }
	
	public int modificarOficinas(String sentencia)
    {
        try
        {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
            apunteLog(MenuPrincipal.nusuario, sentencia);
            return 0;
        }
        catch (SQLException sqle)
        {
            System.out.println("Error 17-" + sqle.getMessage());
            return 1;
        }
    }
	
	public int bajaOficina(Choice choOficinas)
	{
		String idOficina = choOficinas.getSelectedItem();
		String sentencia = "delete from Oficinas where idOficina = '" + idOficina + "'";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			choOficinas.add("Seleccionar Oficina");
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}

	public int bajaOficina(String idOficina)
	{
		  String sentencia = "DELETE FROM oficinas WHERE idOficina = " + idOficina;
		    try {
		        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        statement.executeUpdate(sentencia);
		        apunteLog(MenuPrincipal.nusuario, sentencia);
		        return 0;
		    } catch (SQLException sqle) {
		        System.out.println("Error al eliminar la oficina: " + sqle.getMessage());
		        return 1;
		    }
	}
}
