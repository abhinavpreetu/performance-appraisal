package com.example.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.entities.Designation;
import com.example.entities.Employee;
import com.example.entities.Roles;
import com.example.repository.DesignationRep;
import com.example.repository.EmployeeRepository;
import com.example.repository.RolesRep;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	userDetailsServiceConfig userDetails;
	
	@Autowired
	EmployeeRepository EmployeeRepository;
	
	@Autowired
	DesignationRep desgRep;
	
	@Autowired
	RolesRep roleRep;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
				.and()
				.authorizeRequests()
					.antMatchers("/admin/**").hasAuthority("admin")
					.antMatchers("/user/**").hasAuthority("user")
					/*.anyRequest().authenticated() */
				.and()
				.formLogin()
					.loginPage("/#/login").permitAll()
				.and()
				.logout()
					/*.logoutUrl("/logout")
					.logoutSuccessUrl("/#/login")*/
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/#/login")
				.and()
				.csrf().disable();

	}

	/*
	 * @Autowired protected void configureGlobal (AuthenticationManagerBuilder
	 * auth) throws Exception{
	 * auth.inMemoryAuthentication().withUser("user").password
	 * ("user").roles("USER") .and()
	 * .withUser("admin").password("admin").roles("ADMIN"); }
	 */
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() throws Exception {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName("Spring");
		return entryPoint;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.userDetailsService(userDetails);
		auth.authenticationProvider(authenticationProvider());

	}
	
	@Autowired
	public void defaultSetup(){
		List<Employee> emp=EmployeeRepository.findAll();
		List<Designation> desg = desgRep.findAll();
		Designation designation = new Designation();
		Designation designation1 = new Designation();
		List<Roles> role = roleRep.findAll();
		Employee admin = new Employee();
		Employee employee=new Employee();
		Employee employee2=new Employee();
		Employee employee3=new Employee();
		Employee employee4=new Employee();
		if(desg.isEmpty()){			
			designation.setDesg("Project Manager");	
			desgRep.save(designation);
			designation1.setDesg("Consultant");
			desgRep.save(designation1);
		}		
		if(emp.isEmpty()){			
			admin.setEmail("admin");
			admin.setPassword("admin");
			EmployeeRepository.save(admin);
			
			employee.setFname("qw");
			employee.setLname("qw");
			employee.setEmail("qw");
			employee.setPassword("qw");
			employee.setDesignation(designation);
			EmployeeRepository.save(employee);
						
			employee2.setFname("we");
			employee2.setLname("we");
			employee2.setEmail("we");
			employee2.setPassword("we");
			employee2.setDesignation(designation1);
			EmployeeRepository.save(employee2);
			
			employee3.setFname("er");
			employee3.setLname("er");
			employee3.setEmail("er");
			employee3.setPassword("er");
			employee3.setDesignation(designation1);
			EmployeeRepository.save(employee3);
			
			employee4.setFname("ty");
			employee4.setLname("ty");
			employee4.setEmail("ty");
			employee4.setPassword("ty");
			employee4.setDesignation(designation1);
			EmployeeRepository.save(employee4);
		}
		if(role.isEmpty()){
			Roles role1 = new Roles();
			Roles role2 = new Roles();
			Roles role3 = new Roles();
			Roles role4 = new Roles();
			Roles role5 = new Roles();
			role1.setRole("admin");
			role1.setEmpId(admin);
			roleRep.save(role1);
			
			role2.setRole("user");
			role2.setEmpId(employee);
			roleRep.save(role2);
			
			role3.setRole("user");
			role3.setEmpId(employee2);
			roleRep.save(role3);
			
			role4.setRole("user");
			role4.setEmpId(employee3);
			roleRep.save(role4);
			
			role5.setRole("user");
			role5.setEmpId(employee4);
			roleRep.save(role5);
		}
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetails);

		return authenticationProvider;
	}
}
