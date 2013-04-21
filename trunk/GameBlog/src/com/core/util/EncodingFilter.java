package com.core.util;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Classe Auto encodage UTF8
 * @author LUFFY
 *
 */
public class EncodingFilter implements javax.servlet.Filter {
 	private String encoding;
 	public void init(FilterConfig filterConfig) throws ServletException {
 		this.encoding = filterConfig.getInitParameter("encoding");
 	}
 	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
 		request.setCharacterEncoding(encoding);
 		filterChain.doFilter(request, response);
 	}
 	public void destroy() {
 	}
 }