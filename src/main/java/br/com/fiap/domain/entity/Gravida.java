package br.com.fiap.domain.entity;

public class Gravida {

    private Long id;
    private String nome;
    private int idade;
    private String endereco;
    private String cpf;
    private String email;
    private String cep;
    private String tipo_sanguineo;


    public Gravida(Long id, String nome, int idade, String endereco, String cpf, String email, String cep, String tipo_sanguineo) {
        this.setId(id);
        this.setNome(nome);
        this.setIdade(idade);
        this.setEndereco(endereco);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setCep(cep);
        this.setTipo_sanguineo(getTipo_sanguineo());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipo_sanguineo() {
        return tipo_sanguineo;
    }

    public void setTipo_sanguineo(String tipo_sanguineo) {
        this.tipo_sanguineo = tipo_sanguineo;
    }

    @Override
    public String toString() {
        return "Gravida{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", idade=" + getIdade() +
                ", endereco='" + getEndereco() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cep='" + getCep() + '\'' +
                ", tipo_sanguineo='" + getTipo_sanguineo() + '\'' +
                '}';
    }
}
