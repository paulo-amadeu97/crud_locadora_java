import java.security.MessageDigest;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Usuario extends Pessoa{
    private String user;
    private String senha;

    public Usuario(String nome, int cep, String rua, int numero, String bairro, String cidade, String estado, int mesNascimento, int diaNascimento, int anoNascimento, String email, long celular, String user, String senha) throws NoSuchAlgorithmException {
        super(nome, cep, rua, numero, bairro, cidade, estado, mesNascimento, diaNascimento, anoNascimento, email, celular);
        this.user = user;
        setSenha(senha);
    }

    public Usuario(Pessoa pessoa, String user, String senha) throws NoSuchAlgorithmException {
        super(pessoa.nome,
            (Integer) pessoa.endereco.get("cep"),
            (String) pessoa.endereco.get("rua"),
            (Integer) pessoa.endereco.get("numero"),
            (String) pessoa.endereco.get("bairro"),
            (String) pessoa.endereco.get("cidade"),
            (String) pessoa.endereco.get("estado"),
            (Integer) pessoa.dataNascimento.getMonth(),
            (Integer) pessoa.dataNascimento.getDay(),
            (Integer) pessoa.dataNascimento.getYear(),
            pessoa.email,
            pessoa.celular);
        this.user = user;
        setSenha(senha);
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] senhacrip = md.digest(senha.getBytes());
        this.senha = Base64.getEncoder().encodeToString(senhacrip);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public void locar(Fitas fita, Cliente cliente){
        fita.saida();
        cliente.fitasLocadas.add(fita);
    }

    public void devolucao(Fitas fita, Cliente cliente){
        fita.entrada();
        cliente.fitasLocadas.remove(fita);
    }
}
