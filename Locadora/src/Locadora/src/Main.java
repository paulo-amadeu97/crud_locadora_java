import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        // TESTE COM CPF ERRADO
        try{
            Cliente c2 = new Cliente("Paulo", 78552024,"Rua das perobas", 800, "jd. das Palmeiras", "Sinop", "MT", 2, 5, 1997, "paulo.mendonca@gmail.com", 66992651412L);
            c2.setNumDocu("02473982253");
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            Pessoa pessoaTeste = new Pessoa("Paulo", 78552024,"Rua das perobas", 800, "jd. das Palmeiras", "Sinop", "MT", 2, 5, 1997, "paulo.mendonca@gmail.com", 66992651412L);
            Cliente clienteTeste = new Cliente(pessoaTeste, "02473982252");
            Usuario userTeste = new Usuario(pessoaTeste, "teste.user", "1234");
            System.out.println("Senha criptografada que será armzanada no banco: " + userTeste.getSenha());
            Fitas filmeTeste = new Fitas(120, "Direto teste", "Filme teste", "Terror", 3);
            System.out.println("Quantidade de fitas antes de locar: "+ filmeTeste.getQuantidade());
            userTeste.locar(filmeTeste, clienteTeste);
            System.out.println("Quantidade de fitas depois de locar: "+ filmeTeste.getQuantidade());
            System.out.println(clienteTeste.fitasLocadas);
            ClientePJuri cliente2 = new ClientePJuri(pessoaTeste, "70673833000107");


        }catch (Exception e) {
            System.out.println(e);
        }
    }
}