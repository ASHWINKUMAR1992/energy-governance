package com.ashwin.energygovernance.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ashwin
 * @param <E>
 * @param <K>
 *
 */
@NoRepositoryBean
public interface XRepository<E, K> extends JpaRepository<E, K> {

}
