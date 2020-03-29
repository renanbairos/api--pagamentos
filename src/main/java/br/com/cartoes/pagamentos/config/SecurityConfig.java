package br.com.cartoes.pagamentos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/pagamento").hasRole("TERMINAL")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/pagamentos", "/saldo").hasRole("PORTAL")
                .and()
                .httpBasic();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("terminal")
                .password("{noop}123456")
                .roles("TERMINAL")
                .and()
                .withUser("portal")
                .password("{noop}123456")
                .roles("PORTAL");
    }
}
