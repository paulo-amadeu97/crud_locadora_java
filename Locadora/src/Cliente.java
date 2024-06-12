import java.util.ArrayList;

public class Cliente extends Pessoa{
    protected String numDocu; // CPF
    public ArrayList<Object> fitasLocadas = new ArrayList<Object>();


    public Cliente(String nome, int cep, String rua, int numero, String bairro, String cidade, String estado, int mesNascimento, int diaNascimento, int anoNascimento, String email, long celular) {
        super(nome,
                cep,
                rua,
                numero,
                bairro,
                cidade,
                estado,
                mesNascimento,
                diaNascimento,
                anoNascimento,
                email,
                celular);
    }

    public Cliente(Pessoa pessoa, String cpf) {
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
        this.setNumDocu(cpf);
    }

    public void setNumDocu(String cpf) {
        int nSum = 0;
        for (int i = 0; i < 9; i++) {
            nSum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int pDigVer = nSum % 11;
        if (pDigVer >= 10) {
            pDigVer = 0;
        } else {
            pDigVer = 11 - pDigVer;
        }
        if ((cpf.charAt(9) - '0') != pDigVer || nSum == 0) {
            throw new NullPointerException("CPF INVALIDO");
        } else {
            nSum = 0;
            for (int i = 0; i < 10; i++) {
                nSum += (cpf.charAt(i) - '0') * (11 - i);
            }
            int sDigVer = nSum % 11;
            if (sDigVer >= 10) {
                sDigVer = 0;
            } else {
                sDigVer = 11 - sDigVer;
            }
            if ((cpf.charAt(10) - '0') != sDigVer) {
                throw new NullPointerException("CPF INVALIDO");
            } else {
                this.numDocu = "";
                for (int i = 0; i < 11; i++) {
                    this.numDocu += cpf.charAt(i);
                    if (this.numDocu.length() == 3 || this.numDocu.length() == 7) {
                        this.numDocu += ".";
                    }
                    if (this.numDocu.length() == 11) {
                        this.numDocu += "-";
                    }
                }
            }
        }
    }
}
