package com.example.victoralvesapi.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilther implements Filter{
	
	private String origenPermitida = "http://localhost:8000"; //TODO configurar para diferentes ampientes

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			
			response.setHeader("Access-Control-Allow-Origin", "origenPermitida");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			
			if("OPTIONS".equals(request.getMethod())
					&& origenPermitida.equals(request.getHeaders("Origin"))) {
				response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
				response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type,Accept");
				response.setHeader("Access-Control-Allow-Max-Age", "3600");
				response.setStatus(HttpServletResponse.SC_OK);
				
			}else {
				chain.doFilter(req, resp);
			}
			
		
	}
	

}
