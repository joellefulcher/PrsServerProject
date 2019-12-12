package com.maxtrain.bootcamp.prs.vendor;

import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<Vendor, Integer> { // Integer is the object type, not an
																			// integer
	// Vendor findById(int id);
}
