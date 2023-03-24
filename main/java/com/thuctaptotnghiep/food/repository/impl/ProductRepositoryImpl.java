package com.thuctaptotnghiep.food.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.thuctaptotnghiep.food.entity.Category;
import com.thuctaptotnghiep.food.entity.Origin;
import com.thuctaptotnghiep.food.entity.Product;
import com.thuctaptotnghiep.food.repository.ProductRepositoryCustom;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Product> getByCategoryAndOriginWithPage(Category category, Origin origin, Pageable pageable) {
		StringBuilder sql = new StringBuilder("Select p from Product p");
		if (category != null) {
			sql.append(" where CategoryCode = " + "'" + category.getCategoryCode() + "'");
		}
		if (origin != null) {
			if (category == null)
				sql.append(" where");
			else
				sql.append(" and");
			sql.append(" OriginCode = " + "'" + origin.getOriginCode() + "'");
		}
		Query query = entityManager.createQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Product> listProduct = query.getResultList();
		return new PageImpl<>(listProduct, pageable, listProduct.size());
	}

}
