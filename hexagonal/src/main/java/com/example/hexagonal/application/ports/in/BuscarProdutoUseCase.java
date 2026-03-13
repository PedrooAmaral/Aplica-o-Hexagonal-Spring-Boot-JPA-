package com.example.hexagonal.application.ports.in;

import com.example.hexagonal.domain.model.Produto;


public interface BuscarProdutoUseCase {
    Produto buscarProdutoPorId(Long id);
}
