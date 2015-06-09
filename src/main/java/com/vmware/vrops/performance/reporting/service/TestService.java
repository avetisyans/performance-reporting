package com.vmware.vrops.performance.reporting.service;

import java.util.List;

public interface TestService<T> {

	void save(T testItem);

	T update(T testItem);

	void delete(T testItem);

	T findById(Long id);

	List<T> findByName(String name);

	List<T> findAll();

}
