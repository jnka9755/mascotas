package com.udec.service;

import org.springframework.data.domain.Page;

public abstract interface AbstractFacade <T, V> {
	
	public Page<T> listarPaginado(V page, V size);
	public T listar(V id);
	public void guardar(T entity);
	public void eliminar(V id);
	public void editar(T entity);
}
