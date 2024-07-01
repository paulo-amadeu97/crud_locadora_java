package dao;

import entidades.Fitas;
import entidades.Pessoa;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class FitasDAO {

    public static void save(Fitas fita){
        String sql = "INSERT INTO " +
                "FITAS(TIPO, TITULO, DIRETOR, DURACAO, QUANTIDADE) " +
                "VALUES(?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, fita.getTipo());
            pstm.setString(2, fita.getTitulo());
            pstm.setString(3, fita.getDiretor());
            pstm.setInt(4, fita.getDuracaoMinutos());
            pstm.setInt(5, fita.getQuantidade());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Fitas> getFitas(){
        String sql = "SELECT * FROM FITAS";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<Fitas> fitas = new ArrayList<Fitas>();

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()){
                Fitas fita = new Fitas(rset.getInt("DURACAO"),
                        rset.getString("DIRETOR"),
                        rset.getString("TITULO"),
                        rset.getString("TIPO"),
                        rset.getInt("QUANTIDADE"));
                fita.setIdFita(rset.getInt("ID"));
                fitas.add(fita);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fitas;
    }

    public static Fitas fitaById(int idFita){
        String sql = "SELECT * FROM FITAS WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        Fitas fita = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, fita.getIdFita());

            rset = pstm.executeQuery();

            while (rset.next()){
                fita = new Fitas(rset.getInt("DURACAO"),
                        rset.getString("DIRETOR"),
                        rset.getString("TITULO"),
                        rset.getString("TIPO"),
                        rset.getInt("QUANTIDADE"));
                fita.setIdFita(rset.getInt("ID"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fita;
    }

    public static void update(Fitas fita){
        String sql = "UPDADE FITAS " +
                "SET TIPO = ?, TITULO =? , DIRETOR = ?, DURACAO = ?, QUANTIDADE = ? " +
                "WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm.setString(1, fita.getTipo());
            pstm.setString(2, fita.getTitulo());
            pstm.setString(3, fita.getDiretor());
            pstm.setInt(4, fita.getDuracaoMinutos());
            pstm.setInt(5, fita.getQuantidade());
            pstm.setInt(6, fita.getIdFita());

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

    public static void delete(int idFita){
        String sql = "DELETE FROM FITAS WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idFita);
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
