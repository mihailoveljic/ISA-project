package eu.dreamTeam.isabackend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors()
            .and()
            .csrf()
            .disable()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(new JwtAuthenticationConverter()
            {
                @Override
                protected Collection<GrantedAuthority> extractAuthorities(final Jwt jwt)
                {
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    Map<String, Object> realmAccess = jwt.getClaim("realm_access");
                    log.info(realmAccess.toString());
                    Collection<String> roles = null;
                    if (realmAccess != null && (roles = (Collection<String>) realmAccess.get("roles")) != null)
                        authorities.addAll(roles.stream()
                                .map(x -> {
                                    log.info(x);
                                    return new SimpleGrantedAuthority("ROLE_" + x);
                                })
                                .collect(Collectors.toSet()));
                    return authorities;
                }
            });
    }
}