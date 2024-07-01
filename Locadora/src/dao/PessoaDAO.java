package dao;

import entidades.Pessoa;
import factory.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class PessoaDAO {

    public static void save(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO " +
                "pessoa(NOME, ENDERECO, DNASCIMENTO, EMAIL, CELULAR) " +
                "VALUES(?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm = con.prepareStatement(sql);
            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getEndereco().toString());
            Date d1 = Date.valueOf(pessoa.getDataNascimento().toString());
            pstm.setDate(3, d1);
            pstm.setString(4, pessoa.getEmail());
            pstm.setString(5, String.valueOf(pessoa.getCelular()));

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

    public static List<Pessoa> getPessoas() throws SQLException {
        String sql = "SELECT * FROM PESSOA";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()){
                String[] endereco = rset.getString("ENDERECO").substring(1, rset.getString("ENDERECO").length() - 1).split(", ");
                for(int i=0; i< endereco.length; i++) {
                    endereco[i] = endereco[i].split("=")[1].trim();
                }
                LocalDate dNascimento = rset.getDate("DNASCIMENTO").toLocalDate();
                Pessoa pessoa = new Pessoa(
                        rset.getString("NOME"),
                        Integer.parseInt(endereco[4]),
                        endereco[5],
                        Integer.parseInt(endereco[2]),
                        endereco[3],
                        endereco[0],
                        endereco[1],
                        dNascimento.getMonthValue(),
                        dNascimento.getDayOfMonth(),
                        dNascimento.getYear(),
                        rset.getString("EMAIL"),
                        rset.getLong("CELULAR")
                );
                pessoa.setPessoaId(rset.getInt("ID"));
                pessoas.add(pessoa);
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
        return pessoas;
    }

    public static Pessoa getPessoaById(Integer pessoaId){
        String sql = "SELECT * FROM PESSOA WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Pessoa pessoa = null;
        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, pessoaId);
            rset = pstm.executeQuery();

            String[] endereco = rset.getString("ENDERECO").substring(1, rset.getString("ENDERECO").length() - 1).split(", ");
            for(int i=0; i< endereco.length; i++) {
                endereco[i] = endereco[i].split("=")[1].trim();
            }
            LocalDate dNascimento = rset.getDate("DNASCIMENTO").toLocalDate();
            pessoa = new Pessoa(
                    rset.getString("NOME"),
                    Integer.parseInt(endereco[4]),
                    endereco[5],
                    Integer.parseInt(endereco[2]),
                    endereco[3],
                    endereco[0],
                    endereco[1],
                    dNascimento.getMonthValue(),
                    dNascimento.getDayOfMonth(),
                    dNascimento.getYear(),
                    rset.getString("EMAIL"),
                    rset.getLong("CELULAR")
            );
            pessoa.setPessoaId(rset.getInt("ID"));
        } catch (Exception e){
            e.printStackTrace();
        }finally {
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
        return pessoa;
    }

    public static void update(Pessoa pessoa){
        String sql = "UPDADE PESSOA " +
                "SET NOME = ?, ENDERECO = ?, DNASCIMENTO = ?, EMAIL ?, CELULAR = ? " +
                "WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();

            pstm = con.prepareStatement(sql);
            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getEndereco().toString());
            Date d1 = Date.valueOf(pessoa.getDataNascimento().toString());
            pstm.setDate(3, d1);
            pstm.setString(4, pessoa.getEmail());
            pstm.setString(5, String.valueOf(pessoa.getCelular()));

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

    public static void deleteById(int id){
        String sql = "DELETE FROM PESSOA WHERE ID = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = ConnectionFactory.createConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, id);
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
