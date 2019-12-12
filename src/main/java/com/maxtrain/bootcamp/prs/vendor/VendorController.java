package com.maxtrain.bootcamp.prs.vendor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxtrain.bootcamp.prs.util.JsonResponse;

@CrossOrigin
@RestController
@Controller
@RequestMapping(path = "/vendors")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		Iterable<Vendor> vendors = vendorRepo.findAll();
		return JsonResponse.getInstance(vendors);
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null.");
			Optional<Vendor> v = vendorRepo.findById(id);
			if (!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			return JsonResponse.getInstance(v.get());
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}

	}

	private JsonResponse save(Vendor vendor) {
		try {
			Vendor v = vendorRepo.save(vendor);
			return JsonResponse.getInstance(v);
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}

	@PostMapping()
	public JsonResponse insert(@RequestBody Vendor vendor) {
		try {
			return save(vendor);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}

	}

	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody Vendor vendor, @PathVariable Integer id) {
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null.");
			return save(vendor);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}

	}

	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		// check to make sure there is an user before we update
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null.");
			Optional<Vendor> v = vendorRepo.findById(id);
			if (!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			vendorRepo.deleteById(id);
			return JsonResponse.getInstance(v.get());
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
}
