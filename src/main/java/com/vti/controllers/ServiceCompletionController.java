package com.vti.controllers;

import com.vti.dtos.ServiceCompletionProgressDTO;
import com.vti.entities.ServiceCompletionProgress;
import com.vti.forms.OrderUpdateForm;
import com.vti.repositories.ServiceCompletionRepository;
import com.vti.services.ServiceCompletion;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1/services-completion")
public class ServiceCompletionController {
	@Autowired
	private ServiceCompletion serviceCompletion;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getServicesByOrderId(@RequestParam int orderId) {
		List<ServiceCompletionProgress> services = serviceCompletion.findServicesByOrderId(orderId);

		List<ServiceCompletionProgressDTO> servicesDTO = modelMapper.map(services,
				new TypeToken<List<ServiceCompletionProgressDTO>>() {
				}.getType());
		return new ResponseEntity<>(servicesDTO, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> setServiceStatusIsCompleteByOrderIdAndServiceIds(
			@PathVariable(name = "id", required = true) int id, @RequestParam(name = "ids") List<Integer> ids) {
		serviceCompletion.setServiceStatusIsCompleteByOrderIdAndServiceIds(id, ids);
		return new ResponseEntity<>("Set service status is complete successfully!", HttpStatus.OK);
	}
}
