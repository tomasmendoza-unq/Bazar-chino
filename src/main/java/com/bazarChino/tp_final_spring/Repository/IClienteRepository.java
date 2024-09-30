package com.bazarChino.tp_final_spring.Repository;

import com.bazarChino.tp_final_spring.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente,Long> {
}
