package mate.academy.springboot.datajpa.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product
                .orElseThrow(() -> new NoSuchElementException("Product with id "
                        + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Product retrievedProduct = productRepository.getById(product.getId());
        retrievedProduct.setTitle(product.getTitle());
        retrievedProduct.setPrice(product.getPrice());
        retrievedProduct.setCategory(product.getCategory());
        return productRepository.save(retrievedProduct);
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @Override
    public List<Product> findAllByCategoryIdIn(List<Long> categoriesId) {
        return productRepository.findAllByCategoryIdIn(categoriesId);
    }
}
