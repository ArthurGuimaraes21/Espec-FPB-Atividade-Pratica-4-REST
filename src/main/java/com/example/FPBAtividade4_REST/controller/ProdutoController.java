package com.example.FPBAtividade4_REST.controller;

import com.example.FPBAtividade4_REST.model.Produto;
import com.example.FPBAtividade4_REST.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProduto(@PathVariable Long id) {
        Produto produto = produtoService.obterProdutoPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProduto(@PathVariable Long id, @RequestBody Produto produtoDetalhes)
    {
        Produto produto = produtoService.obterProdutoPorId(id);
        if (produto != null) {
            produto.setNome(produtoDetalhes.getNome());
            produto.setPreco(produtoDetalhes.getPreco());
            return ResponseEntity.ok(produtoService.salvarProduto(produto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}