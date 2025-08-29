package com.example.ecom.service;

import com.example.ecom.model.Product;
import com.example.ecom.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> list(String q) {
        if (q == null || q.isBlank()) return repo.findAll();
        return repo.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(q, q);
    }

    public Product get(Long id) { return repo.findById(id).orElse(null); }
    public Product save(Product p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }
}
