package com.restaurants.finder.common.repository;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AsyncRepository<T, ID extends Serializable> extends Repository<T, ID> {

	long count();

	boolean exists(ID id);

	CompletableFuture<T> findOne(ID id);

	CompletableFuture<List<T>> findAll();

	CompletableFuture<List<T>> findAll(Iterable<ID> ids);

	CompletableFuture<Iterable<T>> findAll(Sort sort);

	CompletableFuture<Page<T>> findAll(Pageable pageable);

	CompletableFuture<T> save(T entity);

	CompletableFuture<Iterable<T>> save(Iterable<T> entities);

	void delete(ID id);

	void delete(T entity);

	void delete(Iterable<T> entities);

	void deleteAll();
}
