package ru.kle10wka.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
		http.authorizeRequests()
			.antMatchers("/hostel", "/hostel/message").access("hasRole('ROLE_HOSTEL')")
			.antMatchers("/admin", "/admin/chooseHostel").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/department", "/department/show", "/department/addGroup",
					"/department/updateGroup", "/department/updatedGroup",
					"/department/show/addStudent", "/department/added-group", 
					"/department/show/updateStudent", "/department/show/deleteStudent")
			.access("hasRole('ROLE_DEPARTMENT')")
				.and().formLogin().loginPage("/login").failureUrl("/login.jsp?error=true")
				.loginProcessingUrl("/j_spring_security_check").usernameParameter("username")
				.passwordParameter("password")
					.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?logout")
						.and().csrf();
	} 	
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
        
    @Bean
    public PasswordEncoder passwordEncoder() {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	return passwordEncoder;
    }
    
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}