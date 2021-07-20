package com.aoyia.machineshop.service;

import com.aoyia.machineshop.domain.Product;
import com.aoyia.machineshop.exception.BadRequestException;
import com.aoyia.machineshop.repository.ManufacturerRepository;
import com.aoyia.machineshop.repository.ProductRepository;
import com.aoyia.machineshop.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;
    private final ManufacturerRepository manufacturerRepository;

    public Product find(Long id){
        return productRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Object not found!"));
    }

    public Page<Product> find(Pageable pageable, Boolean excluded){
        if (excluded == null){
            return this.productRepository.findAll(pageable);
        }
        return this.productRepository.findAll(pageable, excluded);
    }

    public Page<Product> find(Pageable pageable, Product product, String searchType){
        if (product == null){
            return this.productRepository.findAll(pageable);
        }
        if (searchType == null){
            searchType = "and";
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Product> myQuery = Specification.where(null);
        if (product.getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ProductRepository.hasId(product)) : myQuery.and(ProductRepository.hasId(product));
        }
        if (product.getName() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ProductRepository.hasName(product)) : myQuery.and(ProductRepository.hasName(product));
        }
        if (product.getManufacturer() != null && product.getManufacturer().getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ProductRepository.hasManufacturer(product)) : myQuery.and(ProductRepository.hasManufacturer(product));
        }
        if (product.getStatus() != null && product.getStatus().getId() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ProductRepository.hasStatus(product)) : myQuery.and(ProductRepository.hasStatus(product));
        }
        if (product.getExcluded() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ProductRepository.hasExcluded(product)) : myQuery.and(ProductRepository.hasExcluded(product));
        }
        if (product.getModified() != null){
            myQuery = searchType.equals("or") ? myQuery.or(ProductRepository.hasModified(product)) : myQuery.and(ProductRepository.hasModified(product));
        }
        return this.productRepository.findAll(myQuery, pageable);
    }

    public Page<Product> listFind(Pageable pageable, ArrayList<Product> productArrayList, String searchType){
        if (productArrayList == null || productArrayList.isEmpty() || searchType == null){
            return this.productRepository.findAll(pageable);
        }
        if (!searchType.equals("or") && !searchType.equals("and")){
            throw new RuntimeException("Invalid query method. The available methods are 'and' or 'or'.");
        }

        Specification<Product> myQuery = Specification.where(null);
        for (Product product : productArrayList){
            Specification<Product> mySubQuery = Specification.where(null);
            if (product.getId() != null){
                mySubQuery = mySubQuery.and(ProductRepository.hasId(product));
            }
            if (product.getName() != null){
                mySubQuery = mySubQuery.and(ProductRepository.hasName(product));
            }
            if (product.getManufacturer() != null && product.getManufacturer().getId() != null){
                mySubQuery = mySubQuery.and(ProductRepository.hasManufacturer(product));
            }
            if (product.getStatus() != null && product.getStatus().getId() != null){
                mySubQuery = mySubQuery.and(ProductRepository.hasStatus(product));
            }
            if (product.getExcluded() != null){
                mySubQuery = mySubQuery.and(ProductRepository.hasExcluded(product));
            }
            if (product.getModified() != null){
                mySubQuery = mySubQuery.and(ProductRepository.hasModified(product));
            }
            myQuery = searchType.equals("or") ? myQuery.or(mySubQuery) : myQuery.and(mySubQuery);
        };

        return this.productRepository.findAll(myQuery, pageable);
    }

    public Product create(Product product){
        if (product == null){
            throw new RuntimeException("Null object error!");
        }
        if (product.getName().isEmpty()){
            throw new RuntimeException("Empty object name error!");
        }
        if (product.getManufacturer() == null ||
            !this.manufacturerRepository.existsById(product.getManufacturer().getId())
        ){
            throw new RuntimeException("Null manufacturer object error!");
        }
        if (product.getStatus() == null ||
            this.find(product.getStatus().getId()).getExcluded()
        ){
            throw new RuntimeException("Status object error!");
        }
        product.setExcluded(Boolean.FALSE);
        product.setModified(Boolean.FALSE);
        return this.productRepository.save(product);
    }

    public void delete(Long id){
        Product product = this.find(id);
        product.setExcluded(Boolean.TRUE);
        this.productRepository.save(product);
    }

    public Product restore(Long id){
        Product product = this.find(id);
        if (product.getExcluded() != null && !product.getExcluded()){
            throw new RuntimeException("Object has not been deleted!");
        }
        product.setExcluded(Boolean.FALSE);
        return this.productRepository.save(product);
    }

    public Product replace(Product product){
        if (product == null){
            throw new RuntimeException("Null object error!");
        }
        if (!this.productRepository.existsById(product.getId())) {
            throw new RuntimeException("Object not found!");
        }
        if (product.getStatus() == null ||
            this.find(product.getStatus().getId()).getExcluded()
        ){
            throw new RuntimeException("Status object error!");
        }
        this.delete(product.getId());
        product.setModified(Boolean.TRUE);
        return this.create(product);
    }
}