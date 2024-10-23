package com.bazarChino.tp_final_spring.Exception;

import lombok.Data;

@Data
public class ResourceNotFound extends RuntimeException{
    private Long id;

    public ResourceNotFound(Long id, String message) {
        super(message);
        this.id = id;
    }


}
