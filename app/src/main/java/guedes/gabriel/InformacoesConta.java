package guedes.gabriel;

import java.io.Serializable;

public class InformacoesConta implements Serializable {
    private int idConta;
    private String tipo;
    private String nome;
    private double valor;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public InformacoesConta() {
}

public InformacoesConta(int idConta, String tipo, String nome, double valor) {
    this.idConta = idConta;
    this.nome = nome;
    this.tipo = tipo;
    this.valor = valor;
}

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString() {
    return "Conta" +
            "\nID da conta: " + idConta +
            "\nTipo: " + tipo +
            "\nNome: " + nome +
            "\nData: " + data +
            "\nValor: " + valor;
    }
}

