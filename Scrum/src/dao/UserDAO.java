package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.Database;
  
public class UserDAO {      
	
	public static List<String> getUsuarioPerfiles(String user) {
		Connection con = null;
		PreparedStatement ps = null;
		List<String> perfiles = new ArrayList<String>();
		try {
			con = Database.getConnection();
			ps = con.prepareStatement("select perfil from usuario_perfiles where usuario = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				perfiles.add(rs.getString("perfil"));
			} 
		} catch (Exception ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(con);
		}
		return perfiles;
	}
	
	public static List<String> getUsuarios() {
		Connection con = null;
		PreparedStatement ps = null;
		List<String> usuarios = new ArrayList<String>();
		try {
			con = Database.getConnection();
			ps = con.prepareStatement("select usuario from usuarios");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuarios.add(rs.getString("usuario"));
			} 
		} catch (Exception ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(con);
		}
		return usuarios;
	}
	
	public static List<String> insertUsuario(UsuarioEntity user) {
		Connection con = null;
		List<String> usuarios = new ArrayList<String>();
		try {
			con = Database.getConnection();
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO usuarios VALUES ('" + user.getUsuario() + "', '" + user.getClave() + "', '" + user.getCorreo() +"')");
			
		} catch (Exception ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(con);
		}
		return usuarios;
	}
	
	public static List<UsuarioEntity> getUsuariosEntity() {
		Connection con = null;
		PreparedStatement ps = null;
		List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
		try {
			con = Database.getConnection();
			ps = con.prepareStatement("select * from usuarios");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioEntity usuario = new UsuarioEntity();
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
				usuarios.add(usuario);
			} 
		} catch (Exception ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(con);
		}
		return usuarios;
	}
	
	public static UsuarioEntity getUsuarioEntity(String user) {
		Connection con = null;
		PreparedStatement ps = null;
		UsuarioEntity usuario = new UsuarioEntity();
		try {
			con = Database.getConnection();
			ps = con.prepareStatement("select * from usuarios where usuario= ?");
			ps.setString(1, user);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
			} 
		} catch (Exception ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(con);
		}
		return usuario;
	}
	
	public static boolean login(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = Database.getConnection();
			ps = con.prepareStatement(
					"select * from usuarios where usuario= ? and clave= ? ");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
			return false;
		} finally {
			Database.close(con);
		}
	}
}