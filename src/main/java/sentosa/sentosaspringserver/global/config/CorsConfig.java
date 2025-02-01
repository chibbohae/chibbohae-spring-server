package sentosa.sentosaspringserver.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("http://localhost:3000"); // 프론트엔드 도메인만 허용
		config.addAllowedHeader("*");
		config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
