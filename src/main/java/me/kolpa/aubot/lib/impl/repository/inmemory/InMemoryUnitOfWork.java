package me.kolpa.aubot.lib.impl.repository.inmemory;

import me.kolpa.aubot.lib.core.repository.UnitOfWork;
import me.kolpa.aubot.lib.core.repository.domain.GameRepository;
import me.kolpa.aubot.lib.impl.repository.inmemory.domain.InMemoryGameRepository;

public class InMemoryUnitOfWork implements UnitOfWork
{
	private InMemoryGameRepository games;

	public InMemoryUnitOfWork(InMemoryGameRepository games)
	{
		this.games = games;
	}

	@Override
	public GameRepository getGames()
	{
		return games;
	}

	@Override
	public void close()
	{

	}
}
