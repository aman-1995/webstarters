/**
 * 
 */
package com.webstarters.lazycomponents.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Aman Prasad
 *
 */
public interface ComponentDetailsDao {

	List<Map<String, Object>> executeQuery(String string, Map<String, Object> inParams);

}
