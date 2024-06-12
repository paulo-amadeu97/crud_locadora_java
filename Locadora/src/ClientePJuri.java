import java.util.ArrayList;

public class ClientePJuri extends Cliente{
    public ClientePJuri(String nome, int cep, String rua, int numero, String bairro, String cidade, String estado, int mesNascimento, int diaNascimento, int anoNascimento, String email, long celular, String cpf, ArrayList<Object> fitasLocadas) {
        super(nome, cep, rua, numero, bairro, cidade, estado, mesNascimento, diaNascimento, anoNascimento, email, celular, cpf);
    }

    @Override
    public void setNumDocu(String cnpj) {
        int nSum = 0;
        int[] pesosD1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        for(int i = 0; i <= 12; i++){
            int n = (int) cnpj.charAt(i);
            nSum += n * pesosD1[i];
        }
        if(nSum == 0){
            throw new NullPointerException("CNPJ INVALIDO");
        } else {
            int pDigVer = nSum % 11;
            if(pDigVer >= 10){
                pDigVer = 0;
            } else {
                pDigVer = 11 - pDigVer;
            }
            if((int) cnpj.charAt(12) != pDigVer){
                throw new NullPointerException("CNPJ INVALIDO");
            } else {
                nSum = 0;
                int[] pesosD2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
                for(int i = 0; i >= 13; i++){
                    int n = (int) cnpj.charAt(i);
                    nSum += n * pesosD2[i];
                }
                int sDigVer = nSum % 11;
                if(sDigVer >= 10){
                    sDigVer = 0;
                } else {
                    sDigVer = 11 - sDigVer;
                }
                if((int) cnpj.charAt(13) != sDigVer){
                    throw new NullPointerException("CNPJ INVALIDO");
                } else {
                    for(int i = 0; i < 14; i++){
                        this.numDocu += cnpj.charAt(i);
                        if(this.numDocu.length() == 2 || this.numDocu.length() == 6){
                            this.numDocu += ".";
                        } else if (this.numDocu.length() == 10) {
                            this.numDocu += "/";
                        } else if (this.numDocu.length() == 15) {
                            this.numDocu += "-";
                        }
                    }
                }
            }
        }
    }
}
