DROP PROCEDURE IF EXISTS ${storedProdName};
CREATE PROCEDURE ${storedProdName}(${inParams}, forCount INT, limitFrom INT, limitTo INT, sortIndex VARCHAR(100), sortOrder VARCHAR(20))
BEGIN

	SET @selectString = ${selectString!"' '"};
	SET @fromString = ${fromString!"' '"};
	SET @whereString = ' ';
	SET @groupByString = ' ';
	SET @limitString = CONCAT(' LIMIT ','',CONCAT(limitFrom,',',limitTo));

	<#list filterParams as param>	
	IF NOT ${param} IS NULL THEN
    		IF  @whereString != '' THEN
      			SET @whereString = CONCAT(@whereString,' AND column_name LIKE ''%',${param},'%''');
    		ELSE
      			SET @whereString = CONCAT(' WHERE column_name LIKE ''%',${param},'%''');
    		END IF;  
  	END IF;
  	
	</#list>
	
	IF NOT sortIndex IS NULL THEN
      		SET @orderBy = CONCAT(' ORDER BY ' ,sortIndex,' ',sortOrder);
    	ELSE
      		SET @orderBy = ' Default Sorting ';
  	END IF;

	IF forCount=1 THEN
  		SET @queryString=CONCAT('select count(*) from ( ',@selectString, @fromString, @whereString, @groupByString,' ) AS cnt');
  	ELSE
  		SET @queryString=CONCAT(@selectString, @fromString, @whereString, @groupByString, @orderBy, @limitString);
  	END IF;

	PREPARE query FROM @queryString;
 	EXECUTE query;
 	DEALLOCATE PREPARE query;
END;
