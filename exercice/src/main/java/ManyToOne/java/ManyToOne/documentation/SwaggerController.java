package ManyToOne.java.ManyToOne.documentation;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 *
 * “The OpenAPI Specification (OAS) defines a standard, language-agnostic
 * interface to RESTful APIs which allows both humans and computers to discover
 * and understand the capabilities of the service without access to source code,
 * documentation, or through network traffic inspection.” – Swagger website
 * about OpenAPI Specification
 *
 * 
 * @author Aissatou Bobo DIALLO
 * @version 0.0.0
 * @since 0.0.0
 */
@Configuration
public class SwaggerController {
	
      //  @Value("${ili.server.version}")
        private String version;

        /*public static final String JWT_SCHEME_NAME = "Authorization";
        public static final String API_KEY_SCHEME_NAME = "X-API-KEY";
        private String jwtBearerFormat = "JWT";
        private String jwtScheme = "bearer";*/
	
        @Bean
        OpenAPI myOpenAPI() {

                Contact contact = new Contact();
                contact.setEmail("contact@casis.fr");
                contact.setName("Bobo-Entreprise");
                contact.setUrl("https://www.google.fr");

                License idiallo = new License().name("idiallo License").url("https://www.google.fr/");

                Info info = new Info()
                        .title("Server")
                        .version(version)
                        .contact(contact)
                        .description("")
                        .termsOfService("https://www.google.fr")
                        .license(idiallo);

               Components components = new Components();
              /*  components.addSecuritySchemes(
                        JWT_SCHEME_NAME, 
                        new SecurityScheme()
                        .name(JWT_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .bearerFormat(jwtBearerFormat)
                        //.in(SecurityScheme.In.HEADER)
                        .scheme(jwtScheme)
                        ).addSecuritySchemes(
                        API_KEY_SCHEME_NAME,
                        new SecurityScheme()
                        .name(API_KEY_SCHEME_NAME)
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                );
*/
                return new OpenAPI().info(info).components(components);
        }
}
