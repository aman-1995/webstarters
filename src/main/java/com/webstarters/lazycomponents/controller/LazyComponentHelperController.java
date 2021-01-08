package com.webstarters.lazycomponents.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webstarters.lazycomponents.services.impl.LazyComponentHelperService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Aman Prasad
 *
 */

@RestController
@RequestMapping(value = "/lzc")
@CrossOrigin(origins = "*")
public class LazyComponentHelperController {
	
	@Autowired
	private LazyComponentHelperService lzcService = null;
	
	@Autowired
	private Configuration templateConfiguration = null;

	@GetMapping(value = "/sp-template", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStoredProcedureTemplate(@RequestParam(name = "name", required = true) String storedProcedureName,
			@RequestParam(name = "columns", required = false) String columns) throws TemplateException {
		try {
			File fileTemplate = new File(LazyComponentHelperController.class.getResource("/templates/storedProdTemplate.ftl").getFile());
			String templateContent = new String(Files.readAllBytes(fileTemplate.toPath()));
			Template template = new Template("storedProdTemplate", templateContent, templateConfiguration);
			Map<String, Object> templateParams = new HashMap<>();
			templateParams.put("storedProdName", storedProcedureName);
			templateParams.put("inParams", columns);
			templateParams.put("filterParams", columns == null ? new ArrayList<>() : Arrays.asList(columns.split(",")));
			Writer writer = new StringWriter();
			template.process(templateParams, writer);
			return ResponseEntity.ok(writer.toString());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return ResponseEntity.ok(storedProcedureName);
	}
	
	@GetMapping(value = "/table-query-template", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTableTemplate(@RequestParam(name = "tableName", required = true) String storedProcedureName) {
		return ResponseEntity.ok(lzcService.getQueryTemplateForTable(storedProcedureName));
	}
}
