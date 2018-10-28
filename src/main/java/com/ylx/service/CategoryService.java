package com.ylx.service;

import com.ylx.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * Created by LJH
 * 2018-10-09 10:12
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
