/**
 * 
 */
package com.webstarters.lazycomponents.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.webstarters.lazycomponents.vo.ComponentDetailsEntity;

/**
 * @author Aman Prasad
 *
 */
@Repository
public interface ComponentDetailsRepository extends JpaRepositoryImplementation<ComponentDetailsEntity, String>{

}
