public class Cliente {
    private String cpf;
    private String nomeCompleto;
    private String dataNascimento;
    private String sexo;
    private String email;
    private String senha;

    public Cliente(String cpf, String nomeCompleto, String dataNascimento, String sexo, String email, String senha) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
    }

    // GetSet
    public String getCpf() {
        return cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
