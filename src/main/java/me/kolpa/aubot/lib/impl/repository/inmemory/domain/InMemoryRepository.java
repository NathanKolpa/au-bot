package me.kolpa.aubot.lib.impl.repository.inmemory.domain;

import me.kolpa.aubot.lib.core.repository.domain.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryRepository<T> implements Repository<T> 
{
	private List<T> values = new ArrayList<>();
	
	@Override
	public List<T> getAll()
	{
		return new ArrayList<>(values);
	}

	@Override
	public void add(T entity)
	{
		values.add(entity);
	}
	
	public List<T> getValues()
	{
		return values;
	}
}
