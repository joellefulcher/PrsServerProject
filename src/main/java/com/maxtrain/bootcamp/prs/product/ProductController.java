package com.maxtrain.bootcamp.prs.product;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxtrain.bootcamp.prs.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@GetMapping()
	public @ResponseBody JsonResponse getAll() {
		Iterable<Product> p = productRepo.findAll();
		return JsonResponse.getInstance(p);
	}

	@GetMapping("/{id}")
	public @ResponseBody JsonResponse get(@PathVariable Integer id) {
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<Product> p = productRepo.findById(id);
			if (!p.isPresent()) {
				return JsonResponse.getInstance("Product not found");
			}
			return JsonResponse.getInstance(p.get());
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}

	}

	private JsonResponse save(Product product) {
		try {
			Product p = productRepo.save(product);
			return JsonResponse.getInstance(p);
		} catch (IllegalArgumentException ex) {
			return JsonResponse.getInstance("Parameter product cannot be null");
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PostMapping()
	public @ResponseBody JsonResponse insert(@RequestBody Product product) {
		try {
			return save(product);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PutMapping("/{id}")
	public @ResponseBody JsonResponse update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			if (id != product.getId())
				return JsonResponse.getInstance("Parameter id cannot be null");
			return save(product);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse delete(@PathVariable Integer id) {
		// check to make sure there is an user before we update
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<Product> p = productRepo.findById(id);
			if (!p.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			productRepo.deleteById(id);
			return JsonResponse.getInstance(p.get());
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
}
