package com.example.william.alarmap;

import java.io.Serializable;

public class Rua implements Serializable {
    private String endereco;
    private Long id;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  getEndereco() ;
    }
}
