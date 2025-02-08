package sentosa.sentosaspringserver.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sentosa.sentosaspringserver.global.security.auth.jwt.JwtAuthenticationFilter;
import sentosa.sentosaspringserver.global.security.auth.jwt.JwtTokenProvider;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> {}) // 기본 CORS 설정 유지
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/v1/partner/**").hasAuthority("ROLE_PARTNER")
                .requestMatchers("/v1/client/**").hasAuthority("ROLE_CLIENT")
                .requestMatchers("/v1/auth/kakao/**").permitAll()
                .anyRequest().permitAll()
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
