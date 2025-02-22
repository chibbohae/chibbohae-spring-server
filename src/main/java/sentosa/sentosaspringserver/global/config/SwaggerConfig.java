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
				.title("Sentosa Spring Swagger 문서")
				.description("Sentosa Spring 서버 API 명세서입니다.")
				.version("1.0"));
	}
}
