package model;

import exception.AceleracaoNegativaException;

import java.util.Objects;

public class Carro {
    private String cor;
    private String marca;
    private String modelo;
    private Boolean ligado;
    private Integer velocidadeAtual;
    private Integer velocidadeMaxima;
    private Boolean trancado;

    public Carro(String cor, String marca, String modelo, Integer velocidadeMaxima) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.ligado = false;
        this.velocidadeAtual = 0;
        this.velocidadeMaxima = velocidadeMaxima;
        this.trancado = true;
    }

    public Carro() {
        this.ligado = false;
        this.velocidadeAtual = 0;
        this.trancado = true;
    }

    public void ligar() {
        if(!this.ligado) {
            this.ligado = true;
        } else {
            System.out.println("O carro já está ligado");
        }
    }

    public void trancar() {
        if(!trancado) {
            trancado = true;
        } else {
            System.out.println("Ja esta trancado");
        }
    }

    public void destrancar() {
        if(trancado) {
            trancado = false;
        } else {
            System.out.println("Ja esta destrancado");
        }
    }

    public void desligar() {
        if(this.ligado) {
            if(this.velocidadeAtual == 0) {
                this.ligado = false;
            } else {
                System.out.println("O carro deve estar totalmente parado para desligar");
            }
        } else {
            System.out.println("O carro já está desligado");
        }
    }

    public void acelerar(Integer velocidade) throws Exception {

        if(this.ligado) {
            if(velocidade < 0) {
                // Exception - desvio de uma regra ou de um padrão convencionalmente aceito.
                throw new Exception("A aceleracao não pode ser menor que zero!");
            }
            if(this.velocidadeMaxima <= velocidade + velocidadeAtual) {
                this.velocidadeAtual = this.velocidadeMaxima;
            } else {
                this.velocidadeAtual = velocidade + velocidadeAtual;
            }
        } else {
            throw new Exception("A aceleracao não pode ocorrer com o carro desligado!");
        }

    }

    public void frear(Integer velocidade) {

        if(this.ligado) {
            if(this.velocidadeAtual-velocidade <= 0) {
                this.velocidadeAtual = 0;
            } else {
                this.velocidadeAtual = this.velocidadeAtual - velocidade;
            }
        } else {
            System.out.println("O carro pode apenas frear quando estiver ligado!");
        }
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Boolean getLigado() {
        return ligado;
    }

    public Integer getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public Integer getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public Boolean getTrancado() {
        return trancado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(cor, carro.cor) && Objects.equals(marca, carro.marca) && Objects.equals(modelo, carro.modelo) && Objects.equals(ligado, carro.ligado) && Objects.equals(velocidadeAtual, carro.velocidadeAtual) && Objects.equals(velocidadeMaxima, carro.velocidadeMaxima) && Objects.equals(trancado, carro.trancado);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ligado=" + ligado +
                ", velocidadeAtual=" + velocidadeAtual +
                ", velocidadeMaxima=" + velocidadeMaxima +
                ", trancado=" + trancado +
                '}';
    }
}
