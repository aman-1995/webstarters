$(function(){
	$("#tables").select2({
	  placeholder: 'Select an option'
	});
	
	$("#tables").bind("change", function(event){
		getColumns(event.currentTarget.value);
	});
	
	$("#columns").select2({
	  placeholder: 'Select an option'
	});
	$("#queryType").bind("change", function(event){
		if(event.currentTarget.value == "2"){
			$("#spName").prop("disabled", false);
			$("#generateQueryColumns").show();
		} else {
			$("#spName").prop("disabled", true);
			$("#generateQueryColumns").hide();
		}
	});
});

function showTables() {
	$("#generateQuery").show();
	$.ajax({
		type: "GET",
        url: "/lzc/tables",
        success: function(data) {
        	for(let counter=0; counter<data.length; ++counter){
        		let option = new Option(data[counter], data[counter], true, true);
    			$("#tables").append(option);
        	}
        }
	});
}

function getColumns(tableName) {
	let queryType = $("#queryType").val();
	if(queryType == "2") {
		$.ajax({
			type: "GET",
	        url: "/lzc/columns?table="+tableName,
	        success: function(data) {
	        	$("#columns").html("");
	        	for(let counter=0; counter<data.length; ++counter){
	        		let option = new Option(data[counter]['columnName'], data[counter]['columnName'], false, false);
	    			$("#columns").append(option);
        		}
	        }
		});
	}
}

function generateQuery() {
	let queryType = $("#queryType").val();
	let tableName = $("#tables").val();
	let spName = $("#spName").val().trim();
	let columns = $("#columns").val().toString();
	if(queryType == "2") {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			dataType: "text",
	        url: "/lzc/sp-template?tableName="+tableName+"&name="+spName+"&columns="+columns,
	        success: function(data) {
	        	$("#query").val(data);
	        }
		});
	} else {
		$.ajax({
			type: "GET",
	        url: "/lzc/table-query-template?tableName="+tableName,
	        contentType: "application/json",
			dataType: "text",
	        success: function(data) {
	        	$("#query").val(data);
	        }
		});
	}
}