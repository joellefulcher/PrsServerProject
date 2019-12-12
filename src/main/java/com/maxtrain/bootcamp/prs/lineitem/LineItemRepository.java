package com.maxtrain.bootcamp.prs.lineitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, Integer> { // Integer is the object type, not an
																				// integer
	Iterable<LineItem> getLineItemByRequestId(int requestId);
}
