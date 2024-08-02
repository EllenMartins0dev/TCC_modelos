/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.time.LocalDate;

/**
 *
 * @author 1_DEV
 */
public class FluxoDeCaixa {
     private LocalDate data;
    private String descricao;
    private double entrada;
    private double saida;
    
     public FluxoDeCaixa(LocalDate data, String descricao, double entrada, double saida) {
        this.data = data;
        this.descricao = descricao;
        this.entrada = entrada;
        this.saida = saida;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getEntrada() {
        return entrada;
    }

    public double getSaida() {
        return saida;
    }

}
