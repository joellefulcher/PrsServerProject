package com.maxtrain.bootcamp.prs.user;

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
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/authenticate") // Greg preferred authenticate; Sean prefers login
	public JsonResponse authenticate(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			User u = userRepo.findByUsernameAndPassword(username, password);
			if (u == null) {
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u);
		} catch (Exception ex) { // Greg prefers ex instead of e
			return JsonResponse.getInstance(ex.getMessage());
		}
	}

	// KEEP!!! user login
	// good coding practice to use Post instead of Get; Post is more secure
	@PostMapping("/login")
	public JsonResponse userLogin(@RequestBody User u) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(userRepo.findByUsernameAndPassword(u.getUsername(), u.getPassword()));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping()
	public JsonResponse getAll() {
		Iterable<User> u = userRepo.findAll();
		return JsonResponse.getInstance(u);
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null.");
			Optional<User> u = userRepo.findById(id);
			if (!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u.get());
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex);
		}

	}

	private JsonResponse save(User user) {
		try {
			User u = userRepo.save(user);
			return JsonResponse.getInstance(u);
		} catch (Exception ex) { // Greg prefers ex instead of e
			return JsonResponse.getInstance(ex.getMessage());
		}
	}

	@PostMapping() // Post is adding/inserting
	public JsonResponse insert(@RequestBody User user) {
		try {
			return save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}

	}

	@PutMapping("/{id}") // Put is updating
	public JsonResponse update(@RequestBody User user, @PathVariable Integer id) {
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null.");

		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
		return save(user);
	}

	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		// check to make sure there is an user before we update
		try {
			Optional<User> u = userRepo.findById(id);
			if (!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			userRepo.deleteById(id);
			return JsonResponse.getInstance(u.get());
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
}
