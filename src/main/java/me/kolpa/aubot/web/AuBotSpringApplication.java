package me.kolpa.aubot.web;

import me.kolpa.aubot.discord.DiscordBot;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class AuBotSpringApplication extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		ConfigurableApplicationContext context = SpringApplication.run(AuBotSpringApplication.class, args);

		try
		{
			DiscordBot bot = context.getBean(DiscordBot.class);
			bot.init();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		
	}
}
