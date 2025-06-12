package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import vo.PersonaVo;

public class PersonaDao {
	public String registrarUsuario(PersonaVo miPersonaVo)   {
		String resultado="";
		Connection connection= null;
		Conexion conexion =null;
		try {
			
			
			 conexion =new Conexion();
			PreparedStatement preStatement= null; 
			
			connection= conexion.getConnection();
			String consulta="INSERT INTO persona(documento, nombre, telefono, direccion )"+ "VALUES(?,?,?,?)";
			
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersonaVo.getDocumento());
			preStatement.setString(2, miPersonaVo.getNombre());
			preStatement.setString(3, miPersonaVo.getTelefono());
			preStatement.setString(4, miPersonaVo.getDireccion());
			preStatement.execute();
			
			resultado="OK";
			
		} catch (SQLException e) {
			System.err.println("No se pudo registrar el dato"+e.getMessage());
			resultado="No se pudo registrar el dato "+e.getMessage();
		}
		conexion.desconectar();
		
		return resultado;
	}
	

	public ArrayList<PersonaVo>consultarListaPersonas() throws SQLException{
		ArrayList<PersonaVo>listaPersona= new ArrayList<PersonaVo>();
		Connection connection= null;
		Conexion miConexion = new Conexion();	
		PreparedStatement statement=null ;
		ResultSet result= null;
		
		PersonaVo miPersona=null;
		connection = miConexion.getConnection();
		String consulta="SELECT * FROM persona";
		
		try {
			statement = connection.prepareStatement(consulta);
			result = statement.executeQuery();
			
			while(result.next()==true) {
				miPersona=new PersonaVo();
				miPersona.setDocumento(result.getString("Documento"));
				miPersona.setNombre(result.getString("nombre"));
				miPersona.setDireccion(result.getString("Direccion"));
				miPersona.setTelefono(result.getString("telefono"));
				
				listaPersona.add(miPersona);
				
				
			}
		} catch (Exception e) {
			System.err.println("Error en la consulta del usuario"+e.getMessage());
		} finally {
            if (miConexion != null) {
                miConexion.desconectar();
            }
		}
		
		
		return listaPersona;
		
	}


	public boolean eliminarUsuario(PersonaVo personaEliminada) {
		
		try {
			Conexion conexion = new Conexion();
			Connection conectar = conexion.getConnection();
			String sql = "DELETE FROM persona WHERE documento= ?";
				java.sql.PreparedStatement ps= conectar.prepareStatement(sql);
			ps.setString(1, personaEliminada.getDocumento());
			int fila = ps.executeUpdate();
		    ps.close();
		    conectar.close();
		    return fila > 0;
		
		}catch(Exception e) {
		     System.out.println("Error al eliminar persona: " + e.getMessage());
		}
		
		
		
		return false;
	}


	
		

	


}
