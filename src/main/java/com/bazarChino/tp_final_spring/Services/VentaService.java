package com.bazarChino.tp_final_spring.Services;

import com.bazarChino.tp_final_spring.Repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    IVentaRepository ventaRepository;
}
