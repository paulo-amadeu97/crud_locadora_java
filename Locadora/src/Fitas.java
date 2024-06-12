public class Fitas {
    private String tipo;
    private String titulo;
    private String diretor;
    private int duracaoMinutos;
    private int quantidade;

    public Fitas(int duracaoMinutos, String diretor, String titulo, String tipo, int quantidade) {
        this.duracaoMinutos = duracaoMinutos;
        this.diretor = diretor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public void saida(){
        this.quantidade -= 1;
    }

    public void entrada(){
        this.quantidade += 1;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
