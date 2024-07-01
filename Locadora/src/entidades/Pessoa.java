package entidades;

import java.time.LocalDate;
import java.util.HashMap;

public class Pessoa {
    private Integer pessoaId;
    protected String nome;
    protected HashMap<String, Object> endereco = new HashMap<String, Object>();
    protected LocalDate dataNascimento;
    protected String email;
    protected Long celular;

    public Pessoa(String nome, int cep, String rua, int numero, String bairro, String cidade, String estado, int mesNascimento, int diaNascimento, int anoNascimento, String email, Long celular) {
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

    public void setDataNascimento(Integer mesNascimento, Integer diaNascimento, Integer anoNascimento) {
        this.dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);
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

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public HashMap<String, Object> getEndereco() {
        return endereco;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public Long getCelular() {
        return celular;
    }

    @Override
    public String toString() {
        return "id=" + pessoaId +
                "\nnome= " + nome +
                "\nendereco= " + endereco +
                "\ndataNascimento= " + dataNascimento +
                "\nemail= " + email +
                "\ncelular= " + celular;
    }
}
