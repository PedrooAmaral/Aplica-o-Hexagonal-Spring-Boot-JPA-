package com.example.hexagonal.adapters.out.mysql;

import com.example.hexagonal.adapters.out.mysql.entity.ProdutoJpaEntity;
import com.example.hexagonal.adapters.out.mysql.repository.SpringDataProdutoRepository;
import com.example.hexagonal.application.ports.out.ProdutoRepositoryPort;
import com.example.hexagonal.domain.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Adapter de saída para persistência em MySQL.
// Esta classe implementa a porta da aplicação (ProdutoRepositoryPort)
// usando Spring Data JPA por baixo.
@Repository
public class ProdutoMysqlRepository implements ProdutoRepositoryPort {

    // dependência técnica que executa as operações no banco.
    private final SpringDataProdutoRepository repository;

    public ProdutoMysqlRepository(SpringDataProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto salvar(Produto produto) {
        // dominio em entidade
        ProdutoJpaEntity entity = toEntity(produto);
        // .save é o insert ou update do jpa
        ProdutoJpaEntity salvo = repository.save(entity);
        // entidade em dominio
        return toDomain(salvo);
    }

    @Override
    public List<Produto> listar() {
        // .findAll vira select * from produto
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        // .findById vira select * from produto where id = ?
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void removerPorId(Long id) {
        // .deleteById remove pelo id diretamente no banco
        repository.deleteById(id);
    }

    // conversão de entidade JPA para o modelo de domínio
    private Produto toDomain(ProdutoJpaEntity entity) {
        return new Produto(entity.getId(), entity.getNome(), entity.getPreco());
    }

    // conversão de modelo de domínio para entidade JPA
    private ProdutoJpaEntity toEntity(Produto produto) {
        ProdutoJpaEntity entity = new ProdutoJpaEntity();
        entity.setId(produto.getId());
        entity.setNome(produto.getNome());
        entity.setPreco(produto.getPreco());
        return entity;
    }
}
