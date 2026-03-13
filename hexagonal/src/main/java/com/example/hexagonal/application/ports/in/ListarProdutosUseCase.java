package com.example.hexagonal.application.ports.in;

import com.example.hexagonal.domain.model.Produto;
import java.util.List;

public interface ListarProdutosUseCase {
    List<Produto> listarProdutos();
}
