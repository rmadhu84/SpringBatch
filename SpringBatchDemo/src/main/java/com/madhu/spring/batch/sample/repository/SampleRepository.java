/**
 * 
 */
package com.madhu.spring.batch.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.madhu.spring.batch.sample.entity.Sample;

/**
 * @author Madhu
 *
 */
public interface SampleRepository extends CrudRepository<Sample, Long>{

}
