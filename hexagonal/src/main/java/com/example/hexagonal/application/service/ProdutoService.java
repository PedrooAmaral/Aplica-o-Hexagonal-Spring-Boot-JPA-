package com.example.hexagonal.application.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.hexagonal.domain.model.Produto;
import com.example.hexagonal.application.ports.in.CriarProdutoUseCase; 
import com.example.hexagonal.application.ports.in.ListarProdutosUseCase;
import com.example.hexagonal.application.ports.in.BuscarProdutoUseCase;
import com.example.hexagonal.application.ports.in.RemoverProdutoUseCase;
import com.example.hexagonal.application.ports.out.ProdutoRepositoryPort;

// Importações dos casos de uso
@Service
public class ProdutoService implements CriarProdutoUseCase,
 ListarProdutosUseCase, BuscarProdutoUseCase, RemoverProdutoUseCase {

    // Injeção do repositório de produtos via porta de saída
    private final ProdutoRepositoryPort produtoRepository;
    // O construtor recebe o repositório como dependência, seguindo o princípio de inversão de dependência
    public ProdutoService(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Implementação dos métodos dos casos de uso
    @Override
    public Produto criarProduto(Produto produto) {
        // Salva o produto usando a porta de saída e retorna o produto salvo
        return produtoRepository.salvar(produto); 
    }

    @Override
    public List<Produto> listarProdutos() {
        // Lógica para listar produtos
        return produtoRepository.listar(); // Retornar a lista de produtos
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        // Lógica para buscar um produto por ID
        return produtoRepository.buscarPorId(id)
        // Se o produto não for encontrado, lança uma exceção 
        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public void removerProdutoPorId(Long id) {
        // Lógica para remover um produto por ID
        produtoRepository.removerPorId(id); 
    }
    
}
