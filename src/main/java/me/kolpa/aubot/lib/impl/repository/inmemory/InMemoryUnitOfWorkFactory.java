package me.kolpa.aubot.lib.impl.repository.inmemory;

import me.kolpa.aubot.lib.core.repository.UnitOfWork;
import me.kolpa.aubot.lib.core.repository.UnitOfWorkFactory;
import me.kolpa.aubot.lib.impl.repository.inmemory.domain.InMemoryGameRepository;
import org.springframework.stereotype.Service;

@Service
public class InMemoryUnitOfWorkFactory implements UnitOfWorkFactory
{
	private InMemoryGameRepository games = new InMemoryGameRepository();

	@Override
	public UnitOfWork create()
	{
		return new InMemoryUnitOfWork(games);
	}
}
