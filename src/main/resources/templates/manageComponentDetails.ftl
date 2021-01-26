<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<link  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="../css/style.css"/>
<div class="margint container border">
<h4 class="margint">Manage Component Details</h4>
<form>
  <div class="row">
    <div class="form-group col-md-6">
      <label for="componentId">Component Id</label>
      <input type="text" class="form-control" id="componentId" placeholder="Component Id">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Component Description</label>
      <input type="password" class="form-control" id="inputPassword4" placeholder="Component Description">
    </div>
  </div>
  <div class="row">
    <div class="form-group col-md-3">
      <label for="queryType">Query Type</label>
      <select id="queryType" class="form-control">
        <option selected>Choose...</option>
        <option value="1">Query</option>
        <option value="2">Stored Procedure</option>
      </select>
    </div>
    <div class="form-group col-md-3">
      <label for="spName">Stored Procedure Name</label>
      <input type="text" class="form-control" id="spName" placeholder="Stored Procedure Name" disabled="disabled">
    </div>
  </div>
  <div class="margint">
  	<label for="query">Query </label>
  	<textarea id="query" class="form-control"></textarea>
  </div>
  <div class="margint">
  	 <a href="#" onclick="showTables(); return false;"> Generate Query </a>
  </div>
  <div id="generateQuery" class="row" style="display: none;">
  	<div class="form-group col-md-3">
      <label for="tables">Tables</label>
      <select id="tables" style="width: 100%" class="form-control">
      </select>
    </div>
    <div class="form-group col-md-6" id="generateQueryColumns" style="display: none;">
      <label for="columns">Filterable columns</label>
      <select id="columns" style="width: 100%" class="form-control" multiple="multiple">
      </select>
    </div>
    <span class="margint">
     <input type="button" class="btn btn-primary" onclick="generateQuery(); return false;" value="Generate Query">
    </span>
  </div>
  <div class="margint float-end">
	  <input type="button" class="btn btn-primary" value="Save">
	  <input type="button" class="btn btn-secondary" value="Cancel">
  </div>
  <div class="clearfix"></div>
</form>
</div>
<script src="../js/componentDetails.js"></script>