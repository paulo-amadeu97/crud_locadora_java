package dao;

import entidades.Cliente;
import entidades.Pessoa;
import entidades.Usuario;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UsuarioDAO {

    public static void save(Usuario usuario){
        String sql = "INSERT INTO " +
                "USUARIO(PESSOA_ID, LOGIN_USER, SENHA) " +
                "VALUES(?, ?, ?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, usuario.getPessoaId());
            pstm.setString(2, usuario.getUser());
            pstm.setString(3, usuario.getSenha());

            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if (con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static List<Usuario> getUsuarios(){
        String sql = "SELECT * FROM USUARIOS";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            rset = pstm.executeQuery();
            List<Pessoa> pessoas = PessoaDAO.getPessoas();

            while (rset.next()){
                Usuario usuario = new Usuario(
                        PessoaDAO.getPessoaById(rset.getInt("PESSOA_ID")),
                        rset.getString("LOGIN_USER"),
                        rset.getString("SENHA")
                );
                usuario.setUsuarioId(rset.getInt("ID"));
                usuarios.add(usuario);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    public static Usuario usuarioById(int usuarioId){
        String sql = "SELECT * FROM USUARIO WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        Usuario usuario = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, usuarioId);

            rset = pstm.executeQuery();
            List<Pessoa> pessoas = PessoaDAO.getPessoas();
            usuario = new Usuario(
                    PessoaDAO.getPessoaById(rset.getInt("PESSOA_ID")),
                    rset.getString("LOGIN_USER"),
                    rset.getString("SENHA")
            );
            usuario.setUsuarioId(rset.getInt("ID"));

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public static void update(Usuario usuario){
        String sql = "UPDADE USUARIO " +
                "SET PESSOA_ID = ?, LOGIN_USER = ?, SENHA = ? " +
                "WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, usuario.getPessoaId());
            pstm.setString(2, usuario.getUser());
            pstm.setString(3, usuario.getSenha());
            pstm.setInt(4, usuario.getUsuarioId());

            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if (con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void delete(int usuarioId){
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, usuarioId);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if (con!=null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
