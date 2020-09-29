package me.kolpa.aubot.web;

import me.kolpa.aubot.discord.DiscordBot;
import me.kolpa.aubot.lib.core.repository.UnitOfWorkFactory;
import me.kolpa.aubot.lib.impl.repository.inmemory.InMemoryUnitOfWorkFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
	@Bean
	public UnitOfWorkFactory unitOfWorkFactory()
	{
		return new InMemoryUnitOfWorkFactory();
	}
	
	@Bean
	public DiscordBot discordBot(UnitOfWorkFactory unitOfWorkFactory)
	{
		return new DiscordBot(unitOfWorkFactory);
	}
}
