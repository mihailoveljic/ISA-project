/*
package eu.dreamTeam.isabackend.filter;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
@Configuration
@ServletComponentScan
public class ApiKeyFilter implements Filter {
    @Autowired
    private static ApiKeyService apiKeyService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("USAO SAM U JEBENI FILTER");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        var authorizationHeader = httpServletRequest.getHeaderNames();
        ApiKey apiKey = apiKeyService.GetByApiKey(authorizationHeader.toString());
        if(apiKey != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else try {
            throw new Exception("INVALID API-KEY!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/
