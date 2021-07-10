package com.webstarters.lazycomponents.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
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
			@RequestParam(name = "columns", required = false) String columns,
			@RequestParam(name = "tableName", required = false) String tableName) throws TemplateException {
		try {
			Template template = getFreeMakerTemplate("storedProdTemplate");
			Map<String, Object> templateParams = lzcService.getQueryTemplateForStoredProd(tableName, Arrays.asList(columns.split(",")));
			templateParams.put("storedProdName", storedProcedureName);
			Writer writer = new StringWriter();
			template.process(templateParams, writer);
			return ResponseEntity.ok(writer.toString());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return ResponseEntity.ok(storedProcedureName);
	}
	
	@GetMapping(value = "/table-query-template", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTableTemplate(@RequestParam(name = "tableName", required = true) String tableName) {
		return ResponseEntity.ok(lzcService.getQueryTemplateForTable(tableName));
	}
	
	@GetMapping(value = "/manage-component-details", produces = MediaType.TEXT_HTML_VALUE)
	public String getManageComponentDetailsPage(@RequestParam(required = false, name = "id") String componentId) throws IOException, TemplateException {
		Template template = getFreeMakerTemplate("manageComponentDetails");
		Map<String, Object> templateParams = new HashMap<>();
		Writer writer = new StringWriter();
		template.process(templateParams, writer);
		return writer.toString();
	}
	
	@GetMapping(value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getTableDetails() {
		return lzcService.getTablesInformation();
	}

	@GetMapping(value = "/columns", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getTableColumnDetails(@RequestParam(required = true, name="table") String tableName) {
		return lzcService.getTableColumnDetails(tableName);
	}
	
	private Template getFreeMakerTemplate(String templateName) throws IOException {
		templateConfiguration.setAPIBuiltinEnabled(Boolean.TRUE);
		String templateContent = Resources.toString(Resources.getResource("/templates/"+templateName+".ftl"), Charsets.UTF_8);
		Template template = new Template(templateName, templateContent, templateConfiguration);
		return template;
	}
}
