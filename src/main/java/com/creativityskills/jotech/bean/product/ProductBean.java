package com.creativityskills.jotech.bean.product;

import com.creativityskills.jotech.bean.crud.Bean;
import com.creativityskills.jotech.model.Product;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class ProductBean extends Bean<Product> implements ProductBeanI {
}
