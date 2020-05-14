/**
 * 
 */
package com.madhu.spring.batch.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * @author Madhu
 *
 */
@Data
@Entity
@Table(name = "sample", schema = "spring")
public class Sample {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", columnDefinition = "char(30)")
	private String name;

}
