package JIDMU.product.service;

import JIDMU.product.dto.ProductRequest;
import JIDMU.product.dto.ProductResponse;
import JIDMU.product.model.Product;
import JIDMU.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse getProductById(UUID productId) {
        Product product = repository.findById(productId).get();
        return modelMapper.map(product, ProductResponse.class);
    }


    //   ----> we are mapping DAO → DTO
    public List<ProductResponse> getProduct() {
        List<Product> products = repository.findAll();

        List<ProductResponse> dtos = products
                .stream()
                .map(product -> modelMapper.map(product,
                        ProductResponse.class))
                .collect(Collectors.toList());

        return dtos;
    }

    public void create(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest,
                Product.class);
        product.setCreatedAt(Instant.now());
        repository.save(product);
    }
}

