package br.com.fiap.domain.entity;

public class Filho {

    private Long id;
    private String nome;
    private String rg;
    private String tipo_sanguineo;

    public Filho(Long id, String nome, String rg, String tipo_sanguineo) {
        this.setId(id);
        this.setNome(nome);
        this.setRg(rg);
        this.setTipo_sanguineo(tipo_sanguineo);
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTipo_sanguineo() {
        return tipo_sanguineo;
    }

    public void setTipo_sanguineo(String tipo_sanguineo) {
        this.tipo_sanguineo = tipo_sanguineo;
    }

    @Override
    public String toString() {
        return "Filho{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", rg='" + getRg() + '\'' +
                ", tipo_sanguineo='" + getTipo_sanguineo() + '\'' +
                '}';
    }
}
