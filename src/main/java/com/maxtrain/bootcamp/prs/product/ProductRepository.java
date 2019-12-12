package com.maxtrain.bootcamp.prs.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> { // Integer is the object type, not an
																				// integer
	// Product findById(int id);
}
