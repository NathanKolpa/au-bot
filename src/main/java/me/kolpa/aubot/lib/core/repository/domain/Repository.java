package me.kolpa.aubot.lib.core.repository.domain;

import java.util.List;

public interface Repository<T>
{
	List<T> getAll();
	void add(T entity);
}
