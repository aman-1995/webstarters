package com.webstarters.lazycomponents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webstarters.lazycomponents.services.ComponentDetailsService;
import com.webstarters.lazycomponents.vo.ComponentDetails;
import com.webstarters.lazycomponents.vo.ComponentResponseData;

/**
 * @author Aman Prasad
 *
 */

@RestController
@RequestMapping(value = "/lzc")
@CrossOrigin(origins = "*")
public class AutocompleteDataController {

	@Autowired
	private ComponentDetailsService componentService = null;
	
	@PostMapping(value = "load-autocomplete-data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadAutocompleteDetails(@RequestBody(required = true) ComponentDetails componentDetails) {
		componentDetails.setStartIndex();
		ComponentResponseData response = componentService.getCompenentData(componentDetails);
		return ResponseEntity.ok(response);
	}
}
