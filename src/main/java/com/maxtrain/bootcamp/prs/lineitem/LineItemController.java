package com.maxtrain.bootcamp.prs.lineitem;

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

import com.maxtrain.bootcamp.prs.request.Request;
import com.maxtrain.bootcamp.prs.request.RequestController;
import com.maxtrain.bootcamp.prs.request.RequestRepository;
import com.maxtrain.bootcamp.prs.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping(path = "/line-items")
public class LineItemController {

	@Autowired
	private LineItemRepository lineRepo;
	@Autowired
	private RequestRepository requestRepo;

	@GetMapping()
	public JsonResponse getAll() {
		try {
			return JsonResponse.getInstance(lineRepo.findAll());
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@GetMapping("/request/{id}")
	public JsonResponse getByRequest(@PathVariable Integer id) {
		try {
			Iterable<LineItem> lns = lineRepo.getLineItemByRequestId(id);
			return JsonResponse.getInstance(lns);
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	private JsonResponse save(LineItem line) {
		try {
			LineItem li = lineRepo.saveAndFlush(line);
			return JsonResponse.getInstance(li);
		} catch (IllegalArgumentException ex) {
			return JsonResponse.getInstance("Parameter of line item cannot be null");
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex.getMessage());
		}
	}

	@PostMapping()
	private JsonResponse insert(@RequestBody LineItem line) {
		try {
			JsonResponse jr = save(line);
			recalcLines(line.getRequest().getId());
			return jr;
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}
	}

	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody LineItem line, @PathVariable Integer id) {
		try {
			if (id != line.getId()) {
				return JsonResponse.getInstance("Parameter id doesn't match");
			}
			return save(line);
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
				return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<LineItem> l = lineRepo.findById(id);
			if (l.isPresent()) {
				lineRepo.deleteById(id);
				lineRepo.flush();
				recalcLines(l.get().getRequest().getId());
				return JsonResponse.getInstance(l.get());
			}
			return JsonResponse.getInstance("Line item not found");
		} catch (Exception ex) {
			ex.printStackTrace();
			return JsonResponse.getInstance(ex);
		}

	}

	private void recalcLines(int requestId) throws Exception {
		Optional<Request> r = requestRepo.findById(requestId);
		if (!r.isPresent()) {
			throw new Exception("Unable to find this request with this id" + requestId);
		}
		Request request = r.get();
		Iterable<LineItem> lines = lineRepo.getLineItemByRequestId(request.getId());
		double total = 0;
		for (LineItem line : lines) {
			total += line.getQuantity() * line.getProduct().getPrice();
		}
		request.setTotal(total);
		request.setStatus(RequestController.REQUEST_STATUS_EDIT);
		requestRepo.save(request);

	}

}
