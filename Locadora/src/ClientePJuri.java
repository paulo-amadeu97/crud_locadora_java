import java.util.ArrayList;

public class ClientePJuri extends Cliente{
    public ClientePJuri(String nome, int cep, String rua, int numero, String bairro, String cidade, String estado, int mesNascimento, int diaNascimento, int anoNascimento, String email, long celular, String cpf, ArrayList<Object> fitasLocadas) {
        super(nome, cep, rua, numero, bairro, cidade, estado, mesNascimento, diaNascimento, anoNascimento, email, celular);
    }

    public ClientePJuri(Pessoa pessoa, String cnpj) {
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
        this.setNumDocu(cnpj);
    }

    @Override
    public void setNumDocu(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        int nSum = 0;
        int[] pesosD1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            int n = Character.getNumericValue(cnpj.charAt(i));
            nSum += n * pesosD1[i];
        }
        int pDigVer = nSum % 11;
        if (pDigVer < 2) pDigVer = 0;
        else pDigVer = 11 - pDigVer;

        if (pDigVer != Character.getNumericValue(cnpj.charAt(12))) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        nSum = 0;
        int[] pesosD2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            int n = Character.getNumericValue(cnpj.charAt(i));
            nSum += n * pesosD2[i];
        }
        int sDigVer = nSum % 11;
        if (sDigVer < 2) sDigVer = 0;
        else sDigVer = 11 - sDigVer;

        if (sDigVer != Character.getNumericValue(cnpj.charAt(13))) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        // Formatação do CNPJ
        this.numDocu = cnpj.substring(0, 2) + "." +
                cnpj.substring(2, 5) + "." +
                cnpj.substring(5, 8) + "/" +
                cnpj.substring(8, 12) + "-" +
                cnpj.substring(12);
    }
}
