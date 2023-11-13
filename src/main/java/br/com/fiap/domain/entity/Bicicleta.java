package br.com.fiap.domain.entity;




public class Bicicleta {


    private Long id;

    private String tipo_bicicleta;

    private Cliente dono;


    public Bicicleta() {
    }

    public Bicicleta(Long id, String tipo_bicicleta, Cliente dono) {
        this.setId(id);
        this.setTipo_bicicleta(tipo_bicicleta);
        this.setDono(dono);
    }


    public Long getId() {
        return id;
    }

    public Bicicleta setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTipo_bicicleta() {
        return tipo_bicicleta;
    }

    public Bicicleta setTipo_bicicleta(String tipo_bicicleta) {
        this.tipo_bicicleta = tipo_bicicleta;
        return this;
    }

    public Cliente getDono() {
        return dono;
    }

    public Bicicleta setDono(Cliente dono) {
        this.dono = dono;
        return this;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id=" + id +
                ", tipo_bicicleta='" + tipo_bicicleta + '\'' +
                ", dono=" + dono +
                '}';
    }
}