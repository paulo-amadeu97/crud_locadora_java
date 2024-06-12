import java.util.HashMap;
import java.util.Date;

public class Pessoa {
    protected String nome;
    protected HashMap<String, Object> endereco = new HashMap<String, Object>();
    protected Date dataNascimento;
    protected String email;
    protected long celular;

    public Pessoa(String nome, int cep, String rua, int numero, String bairro, String cidade, String estado, int mesNascimento, int diaNascimento, int anoNascimento, String email, long celular) {
        if (nome == null) {
            throw new NullPointerException("Nome e/ou CPF/CNPJ são de preenchimento obrigatório");
        } else {
            this.nome = nome;
            setEndereco(cep, rua, numero, bairro, cidade, estado);
            setDataNascimento(mesNascimento, diaNascimento, anoNascimento);
            setEmail(email);
            this.celular = celular;
        }
    }

    public void setEndereco(int cep, String rua, int numero, String bairro, String cidade, String estado) {
        this.endereco.put("cep", cep);
        this.endereco.put("rua", rua);
        this.endereco.put("numero", numero);
        this.endereco.put("bairro", bairro);
        this.endereco.put("cidade", cidade);
        this.endereco.put("estado", estado);
    }

    public void setDataNascimento(int mesNascimento, int diaNascimento, int anoNascimento) {
        this.dataNascimento = new Date(anoNascimento - 1900, mesNascimento - 1, diaNascimento);
    }

    public void setEmail(String email) {
        if (countCaracter(email, '@') == 1) {
            String[] listmail = email.split(String.valueOf("@"));
            if (listmail[1].contains(String.valueOf("."))) {
                this.email = email;
            } else {
                throw new NullPointerException("email invalido");
            }
        } else {
            throw new NullPointerException("email invalido");
        }
    }

    private int countCaracter(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

}
