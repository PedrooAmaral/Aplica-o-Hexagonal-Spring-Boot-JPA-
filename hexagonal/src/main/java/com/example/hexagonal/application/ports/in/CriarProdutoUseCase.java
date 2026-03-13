package com.example.hexagonal.application.ports.in;

import com.example.hexagonal.domain.model.Produto;

public interface CriarProdutoUseCase {
    Produto criarProduto(Produto produto);
}
