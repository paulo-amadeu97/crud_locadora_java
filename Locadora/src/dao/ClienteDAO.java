package dao;

import entidades.Cliente;
import entidades.Pessoa;
import factory.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ClienteDAO{

    public static void save(Cliente cliente){
        String sql = "INSERT INTO " +
                "CLIENTES(PESSOA_ID, DOCUM) " +
                "VALUES(?, ?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cliente.getPessoaId());
            pstm.setString(2, cliente.getNumDocu());

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

    public static List<Cliente> getClientes() {
        String sql = "SELECT * FROM CLIENTES";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            rset = pstm.executeQuery();
            List<Pessoa> pessoas = PessoaDAO.getPessoas();

            while (rset.next()){
                Cliente cliente = new Cliente(
                        PessoaDAO.getPessoaById(rset.getInt("PESSOA_ID")),
                                rset.getString("DOCUM")
                );
                cliente.setClienteId(rset.getInt("ID"));
                clientes.add(cliente);
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
        return clientes;
    }

    public static Cliente getClienteById(int clienteId){
        String sql = "SELECT * FROM CLIENTES WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        Cliente cliente = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, clienteId);

            rset = pstm.executeQuery();
            List<Pessoa> pessoas = PessoaDAO.getPessoas();
            cliente = new Cliente(
                    PessoaDAO.getPessoaById(rset.getInt("PESSOA_ID")),
                    rset.getString("DOCUM")
            );
            cliente.setClienteId(rset.getInt("ID"));

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
        return cliente;
    }

    public static void update(Cliente cliente){
        String sql = "UPDADE CLIENTES " +
                "SET PESSOA_ID = ?, DOCUM = ? " +
                "WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, cliente.getPessoaId());
            pstm.setString(2, cliente.getNumDocu());
            pstm.setInt(3, cliente.getClienteId());

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

    public static void delete(int clienteId){
        String sql = "DELETE FROM CLIENTES WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, clienteId);
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
