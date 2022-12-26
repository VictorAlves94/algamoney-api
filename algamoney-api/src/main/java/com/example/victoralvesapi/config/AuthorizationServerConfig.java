package com.example.victoralvesapi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("angular")
			.secret("$2a$10$Nb/uiTP.3EU0pxWLV7/ecOH9NezNVcOkuDvQnbBh.kFpZ7pPO.NZq")
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600 * 24)
			.and()
			.withClient("mobile")
			.secret("$2a$10$NQWCgq68Xh3ukAD7K/VvvuBhmHmsp4FfEBIydB3lu10Gj4UEqjANW")
			.scopes("read")
			.authorizedGrantTypes("password", "refresh_token")
			.accessTokenValiditySeconds(1800)
			.refreshTokenValiditySeconds(3600 * 24);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())
			.reuseRefreshTokens(false)
			.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("88CB851F47F64DE11ED656D75B6FA9A72FA8787A45A7FBA4783D3F5D59326DA875EB174BFA12C53D4762768\"");
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}