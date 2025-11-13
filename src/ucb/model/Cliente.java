package ucb.model;



public class Cliente {
    private String cpf; // ID principal
    private String nome;
    private String sobrenome;
    private String primeiroNome; // Usando "PrimeiroNome" para o atributo "Primeiro" do DER
    private String telefone;


    public Cliente(String cpf, String nome, String sobrenome, String primeiroNome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.primeiroNome = primeiroNome;
        this.telefone = telefone;
    }


    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getTelefone() {
        return telefone;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "CPF: " + cpf +
                " | Nome Completo: " + primeiroNome + " " + sobrenome +
                " | Nome: " + nome +
                " | Telefone: " + telefone;
    }
}
