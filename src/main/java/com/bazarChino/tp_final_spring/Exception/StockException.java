package com.bazarChino.tp_final_spring.Exception;

import lombok.Data;

@Data
public class StockException  extends RuntimeException{

    private Double stock;

    public StockException(Double stock, String message) {
        super(message);
        this.stock = stock;
    }
}
