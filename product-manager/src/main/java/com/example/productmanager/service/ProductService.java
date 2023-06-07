package com.example.productmanager.service;

import com.example.productmanager.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService implements IProductService{
    private static Map<Integer,Product> products;
    static {
        products = new HashMap<>();
        products.put(1,new Product(1,"Huda Beer Can",10000,"un la say","Carls Berg"));
        products.put(2,new Product(2,"Coca-cola Can",8000,"nuoc uong co ga","Coca"));
        products.put(3,new Product(3,"My tom Hao Hao",5000,"nau len an","Hao Hao"));
        products.put(4,new Product(4,"Muc bento",5000,"an cay cay","Bento"));
        products.put(5,new Product(5,"Ca hop",20000,"trong hop co ca thu","Do hop Ha Long"));
        products.put(6,new Product(6,"Ao mua tien loi",7000,"mac vao khong bi dinh mua","Ao mua"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findAllByName(String name) {
        return  findAll().stream().filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()) ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

}
