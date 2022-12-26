package com.example.victoralvesapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties("algaMoney")
public class AlgamoneyApiProperty {
	
	private final static Seguranca seguranca = new Seguranca();
	
	public static Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca{
		
	private boolean enableHttps;
	
	public boolean isEnableHttps() {
		return enableHttps;
	}
	public void setEnableHttps(boolean enableHttps) {
		this.enableHttps = enableHttps;	
	}

}
}
