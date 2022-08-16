package com.nnk.springboot.config;

import com.nnk.springboot.services.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.validation.constraints.NotNull;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService uds;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds).passwordEncoder(passwordEncoder());
   }
/*
    @Override
    public void configure(@NotNull HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login**","/index**", "/js/**", "/css/**", "/img/**")
                .permitAll()
                .antMatchers("/user/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService);
    }
*/
    @Override
   protected void configure(@NotNull HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login**", "/templates/**", "/css/*.css", "/img/**").permitAll()
                .antMatchers("/user/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/bidList/list", true)
                .failureUrl("/login.html")
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .invalidateHttpSession(true).clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID");

/*        httpSecurity.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated();
        httpSecurity.formLogin().loginPage("/index").permitAll().loginProcessingUrl("/login").defaultSuccessUrl("/home", true)
                .failureUrl("/403.html");
*/

/*
        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
//        httpSecurity.authorizeRequests().antMatchers("/resources/static.css/*.css", "/resources/templates/*.html", "/resources/**").permitAll()
       httpSecurity.authorizeRequests().antMatchers("/user/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/index").permitAll();

        httpSecurity.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login").defaultSuccessUrl("/", true)
                .failureUrl("/403");

        httpSecurity.logout().logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID");
        */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Value("${spring.security.debug:true}")
    boolean securityDebug;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(securityDebug);
    }
}
