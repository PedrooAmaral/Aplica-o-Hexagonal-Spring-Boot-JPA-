package com.example.hexagonal.adapters.out.mysql.repository;

import com.example.hexagonal.adapters.out.mysql.entity.ProdutoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//fornece .save e .findAll, .findById, .deleteById 

public interface SpringDataProdutoRepository extends JpaRepository<ProdutoJpaEntity, Long> {
}
