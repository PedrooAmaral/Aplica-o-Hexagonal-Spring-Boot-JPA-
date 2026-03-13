package com.example.hexagonal.application.ports.out;

import com.example.hexagonal.domain.model.Produto;
import java.util.List;
import java.util.Optional;
//Porta de saída para o repositório de produtos
//No adaptador de saída, implementaremos essa interface usando um bd ou array. 
// Nessa etapa, não nos preocupamos com a implementação, apenas com a definição do contrato que o adaptador deve seguir
public interface ProdutoRepositoryPort {
    //salva o produto e retorna o produto salvo, com o ID gerado
    Produto salvar(Produto produto);
    //retorna uma lista, mesmo que esteja vazia
    List<Produto> listar();
    //pode encontrar ou não o produto, por isso o Optional
    Optional<Produto> buscarPorId(Long id);
    //remove o produto por ID, se existir
    void removerPorId(Long id);
}
