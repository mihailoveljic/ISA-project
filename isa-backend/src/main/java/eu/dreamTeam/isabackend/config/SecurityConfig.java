package eu.dreamTeam.isabackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
                    Map<String, Object> resource = null;
                    Collection<String> resourceRoles = null;
                    if (resourceAccess != null &&
                            (resource = (Map<String, Object>) resourceAccess.get("wedofinance-frontend")) !=
                                    null && (resourceRoles = (Collection<String>) resource.get("roles")) != null)
                        authorities.addAll(resourceRoles.stream()
                                .map(x -> new SimpleGrantedAuthority("ROLE_" + x))
                                .collect(Collectors.toSet()));
                    return authorities;
                }
            });
    }
}