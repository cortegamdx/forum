package br.com.alura.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration //quando startar o projeto o spring le as configurações de classe
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	//Configuraçoes de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	
	}
	
	//Configuracoes de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/topicos").permitAll()
		.antMatchers(HttpMethod.GET,"/topicos/*").permitAll();

	}
	
	//Configuraçoes de recursos staticos(js,css,imagens, etc...)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
