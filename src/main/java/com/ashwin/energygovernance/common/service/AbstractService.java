package com.ashwin.energygovernance.common.service;

import java.util.NoSuchElementException;
import com.ashwin.energygovernance.common.exception.ResourceNotFoundException;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.entity.TableEntity;

// This class Represents the abstract service which can be use as Generic service.
/**
 * 
 * @author ashwin
 *
 * @param <E>
 */
public abstract class AbstractService<E extends TableEntity<K>,K> {


  /**
   * 
   * @param entity
   * @return
   * @throws ResourceNotFoundException
   */
  public E insertResource(E entity) throws ResourceNotFoundException {
    getRepository().save(entity);
    return findResourceById(entity.getId());
  }

  /**
   * 
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public E findResourceById(K id) throws ResourceNotFoundException {
    try {
      return getRepository().findById(id).get();
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(e, getEntityName());
    }
  }



  /**
   * 
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public E deleteResource(K id) throws ResourceNotFoundException {
    E e = findResourceById(id);
    getRepository().deleteById(id);
    return e;
  }

  protected abstract String getEntityName();

  public abstract XRepository<E,K> getRepository();

}
