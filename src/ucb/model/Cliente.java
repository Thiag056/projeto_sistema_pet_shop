package ucb.model;

public class Cliente {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String telefone;


    public Cliente(String cpf, String nome, String sobrenome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }


    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getTelefone() { return telefone; }

    public void setNome(String nome) { this.nome = nome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {

        return "CPF: " + cpf +
                " | Nome: " + nome +
                " | Sobrenome: " + sobrenome +
                " | Telefone: " + telefone;
    }
}