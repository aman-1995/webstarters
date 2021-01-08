package com.webstarters.lazycomponents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class GridDataController {
	
	@Autowired
	private ComponentDetailsService componentService = null;
	
	@PostMapping(value = "/load-grid-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadGridDetails(@RequestBody(required = true) ComponentDetails componentDetails) {
		try {
			componentDetails.setStartIndex();
			ComponentResponseData response = componentService.getCompenentData(componentDetails);
			return ResponseEntity.ok(response);
		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occured");
		}
	}

}
