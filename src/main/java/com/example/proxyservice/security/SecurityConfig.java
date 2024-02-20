package com.example.proxyservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterRequest(HttpSecurity http) throws Exception {
//
//        //http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
//        http.authorizeHttpRequests((authorize)->
//                        authorize.requestMatchers("/products").hasAuthority("admin")
//                                .anyRequest().permitAll())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())));
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/*").permitAll()
                        .anyRequest().permitAll()

                )
                //.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .cors().disable()
                .csrf().disable();
        return http.build();

    }

    //    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/products").hasAuthority("mentor")
//                        .requestMatchers("/products/{id}").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
//                        jwtConfigurer -> {
//                            jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
//                        }
//                ))
//        // Form login handles the redirect to the login page from the
//        // authorization server filter chain
//        ;
//
//        return http.build();
//    }
}


//@Configuration
////@EnableElasticsearchRepositories(considerNestedRepositories = true, enableDefaultTransactions = true)
////@EnableElasticsearchRepositories(basePackages = "com.example.proxyservice.repository")
////@ComponentScan(basePackages = { "com.example.proxyservice.services" })
////@EntityScan(basePackages = {"com.example.proxyservice.models"})
//public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//
//        return RestClients.create(clientConfiguration)
//                .rest();
//    }
//}
