package com.itcast.service.impl;

import com.itcast.dao.IProductDao;
import com.itcast.domain.Product;
import com.itcast.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<Product> productList = productDao.findAll ();
        return productList;
    }

    @Override
    public void save(Product product){
        productDao.save ( product );
    }
}
