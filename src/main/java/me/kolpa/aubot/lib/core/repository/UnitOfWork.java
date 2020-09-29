package me.kolpa.aubot.lib.core.repository;

import me.kolpa.aubot.lib.core.repository.domain.GameRepository;

public interface UnitOfWork extends AutoCloseable
{
	GameRepository getGames();
}
