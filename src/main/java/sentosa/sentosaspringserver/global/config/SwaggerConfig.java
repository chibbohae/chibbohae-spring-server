package sentosa.sentosaspringserver.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Sentosa Srping Swagger 문서")
				.description("Sentsoa Spring API 명세서입니다.")
				.version("1.0"));
	}
}
