package com.example.hexagonal.adapters.in.controller;

import com.example.hexagonal.application.ports.in.BuscarProdutoUseCase;
import com.example.hexagonal.application.ports.in.CriarProdutoUseCase;
import com.example.hexagonal.application.ports.in.ListarProdutosUseCase;
import com.example.hexagonal.application.ports.in.RemoverProdutoUseCase;
import com.example.hexagonal.domain.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define que esta classe é um controller REST (retorna JSON por padrão)
@RestController
// Prefixo de rota para todos os endpoints desta classe
@RequestMapping("/produtos")
public class ProdutoController {

    // Portas de entrada (casos de uso) que o controller chama
    // O controller não acessa repositório diretamente: ele delega para a camada de aplicação
    private final CriarProdutoUseCase criarProdutoUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final RemoverProdutoUseCase removerProdutoUseCase;

    // Injeção de dependência por construtor (boa prática no Spring)
    public ProdutoController(CriarProdutoUseCase criarProdutoUseCase,
                             ListarProdutosUseCase listarProdutosUseCase,
                             BuscarProdutoUseCase buscarProdutoUseCase,
                             RemoverProdutoUseCase removerProdutoUseCase) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.removerProdutoUseCase = removerProdutoUseCase;
    }

    // POST /produtos
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto produtoSalvo = criarProdutoUseCase.criarProduto(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    // GET /produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(listarProdutosUseCase.listarProdutos());
    }

    // GET /produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(buscarProdutoUseCase.buscarProdutoPorId(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerProdutoUseCase.removerProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
