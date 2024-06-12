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

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] senhacrip = md.digest(senha.getBytes());
        this.senha = Base64.getEncoder().encodeToString(senhacrip);
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public void locar(ArrayList<Fitas> fita, Cliente cliente){
        for(int i = 0; i > fita.size(); i++){
            fita.get(i).saida();
            cliente.fitasLocadas.add(fita.get(i));
        }
    }

    public void devolucao(ArrayList<Fitas> fita, Cliente cliente){
        for(int i = 0; i > fita.size(); i++){
            fita.get(i).entrada();
            cliente.fitasLocadas.remove(fita.get(i));
        }
    }
}
