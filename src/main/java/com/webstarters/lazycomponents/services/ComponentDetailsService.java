package com.webstarters.lazycomponents.services;

import com.webstarters.lazycomponents.vo.ComponentDetails;
import com.webstarters.lazycomponents.vo.ComponentDetailsVo;
import com.webstarters.lazycomponents.vo.ComponentResponseData;

public interface ComponentDetailsService {

	/**
	 * @param componentId
	 * @return
	 */
	public ComponentDetailsVo getComponentDetailsByComponentId(String componentId);
	
	/**
	 * @param componentDetails
	 * @return
	 */
	public ComponentResponseData getCompenentData(ComponentDetails componentDetails);
	
}
