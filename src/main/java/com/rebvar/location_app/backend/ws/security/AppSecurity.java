package com.rebvar.location_app.backend.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rebvar.location_app.backend.ws.AppConstants;
import com.rebvar.location_app.backend.ws.service.UserService;

/**
 * @author sehossei
 *  Configures the security properties of the rest api. Provides a list of publicly available endpoints such as register
 *  login, and anonymous searches.
 */
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {

	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptEncoder;
	
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers(AppConstants.H2_CONSOLE).permitAll()
		.antMatchers(HttpMethod.POST, AppConstants.SIGN_UP_URL).permitAll()
		.antMatchers(HttpMethod.POST, AppConstants.LOGIN_URL).permitAll()
        .antMatchers(HttpMethod.GET, 
        		AppConstants.MAP_CITY_ENDPOINT, AppConstants.MAP_COUNTRY_ENDPOINT, 
        		AppConstants.MAP_CONTINENT_ENDPOINT,AppConstants.MAP_SEARCH_ENDPOINT,
        		AppConstants.SECURITY_ENABLED? AppConstants.INVALID_ENDPOINT : AppConstants.MAP_ENDPOINT
        		).permitAll()
        .antMatchers(HttpMethod.POST, AppConstants.SECURITY_ENABLED? AppConstants.INVALID_ENDPOINT : AppConstants.MAP_ENDPOINT).permitAll()
        .antMatchers(HttpMethod.DELETE, AppConstants.SECURITY_ENABLED? AppConstants.INVALID_ENDPOINT : AppConstants.MAP_ENDPOINT).permitAll()
		.anyRequest().authenticated().and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authManager) throws Exception
	{
		authManager.userDetailsService(userDetailsService).passwordEncoder(bCryptEncoder);
	}
	
	
	public AppSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptEncoder)
	{
		this.bCryptEncoder = bCryptEncoder;
		this.userDetailsService = userDetailsService;
	}
	
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
	    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	    //Set the address for the login endpoint
	    filter.setFilterProcessesUrl(AppConstants.LOGIN_URL);
	    return filter;
	}
	
}