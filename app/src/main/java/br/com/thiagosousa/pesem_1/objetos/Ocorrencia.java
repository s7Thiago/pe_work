package br.com.thiagosousa.pesem_1.objetos;

public class Ocorrencia {

    private int valor;
    private String descricao;

    public Ocorrencia(int valor) {
        setValor(valor);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao + "\nvalor: " + valor;
    }
}
