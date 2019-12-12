package com.maxtrain.bootcamp.prs.request;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/requests")
public class RequestController {
	private static final String REQUEST_STATUS_NEW = "New";
	public static final String REQUEST_STATUS_EDIT = "Edit";
	private static final String REQUEST_STATUS_REVIEW = "Review";
	private static final String REQUEST_STATUS_APPROVED = "Approved";
	private static final String REQUEST_STATUS_REJECTED = "Rejected";

	@Autowired
	private RequestRepository requestRepo;

	@GetMapping()
	public JsonResponse getAll() {
		try {
			Iterable<Request> request = requestRepo.findAll();
			return JsonResponse.getInstance(request);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex.getMessage());
		}
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<Request> r = requestRepo.findById(id);
			if (!r.isPresent()) {
				return JsonResponse.getInstance("Request not found.");
			}
			return JsonResponse.getInstance(r.get());
		} catch (IllegalArgumentException ex) {
			return JsonResponse.getInstance("Id cannot be null.");
		} catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}

	}

	private JsonResponse save(Request request) {
		try {
			return JsonResponse.getInstance(requestRepo.save(request));
		} catch (IllegalArgumentException ex) {
			return JsonResponse.getInstance(ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex.getMessage());
		}
	}

	@PostMapping()
	public JsonResponse insert(@RequestBody Request request) {
		try {
			request.setStatus(REQUEST_STATUS_NEW);
			request.setTotal(0);
			request.setSubmittedDate(new Date(System.currentTimeMillis()));
			return save(request);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody Request request, @PathVariable Integer id) {
		try {
			if (id != request.getId()) {
				return JsonResponse.getInstance("Parameter id doesn't match request.");
			}
			return save(request);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		try {
			if (id == null)
				return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<Request> r = requestRepo.findById(id);
			if (!r.isPresent())
				return JsonResponse.getInstance("Request not found");
			requestRepo.deleteById(r.get().getId());
			return JsonResponse.getInstance(r.get());
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PutMapping("/reviews/{userId}")
	public JsonResponse getRequestWithStatusOfReview(@PathVariable Integer userId) {
		try {
			if (userId == null)
				return JsonResponse.getInstance("UserId parameter cannot be null.");
			Iterable<Request> r = requestRepo.getRequestByStatusAndUserIdNot(REQUEST_STATUS_REVIEW, userId);
			return JsonResponse.getInstance(r);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	private JsonResponse setRequestStatus(Request request, String status) {
		try {
			request.setStatus(status);
			return save(request);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PutMapping("/approve/{id}")
	public JsonResponse approve(@RequestBody Request request, @PathVariable Integer id) {
		try {
			if (id != request.getId()) {
				return JsonResponse.getInstance("Parameter id doesn't match request");
			}
			return setRequestStatus(request, REQUEST_STATUS_APPROVED);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PutMapping("/reject/{id}")
	public JsonResponse reject(@RequestBody Request request, @PathVariable Integer id) {
		try {
			if (id != request.getId()) {
				return JsonResponse.getInstance("Parameter id doesn't match request");
			}
			return setRequestStatus(request, REQUEST_STATUS_REJECTED);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}

	}

	@PutMapping("/review/{id}")
	public JsonResponse review(@RequestBody Request request, @PathVariable Integer id) {
		try {
			if (id != request.getId())
				return JsonResponse.getInstance("Parameter id doesn't match");
			request.setSubmittedDate(new Date(System.currentTimeMillis()));
			if (request.getTotal() <= 50) {
				return setRequestStatus(request, REQUEST_STATUS_APPROVED);
			}
			return setRequestStatus(request, REQUEST_STATUS_REVIEW);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

}
