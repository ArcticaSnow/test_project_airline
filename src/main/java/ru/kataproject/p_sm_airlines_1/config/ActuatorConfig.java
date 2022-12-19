package ru.kataproject.p_sm_airlines_1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springdoc.core.Constants;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.providers.ActuatorProvider;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class ActuatorConfig {

    @Bean
    InfoContributor licenseContributor(final ObjectMapper objectMapper) {
        final Logger log = LogManager.getLogger(InfoContributor.class);

        return builder -> {
            try {
                final Map<String, Object> nodes = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream("license-mapping.json"), objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class));
                builder.withDetails(nodes);
            } catch (IOException e) {
                log.error("Error retrieving license information: ".concat(e.getMessage()));
            }
        };
    }

    @Bean
    ActuatorProvider actuatorProvider(Optional<ManagementServerProperties> managementServerProperties, Optional<WebEndpointProperties> webEndpointProperties, ServerProperties serverProperties, SpringDocConfigProperties springDocConfigProperties) {
        return new TemplateManagerWebMvcActuatorProvider(managementServerProperties, webEndpointProperties, serverProperties, springDocConfigProperties);
    }

    static class TemplateManagerWebMvcActuatorProvider extends ActuatorProvider {

        public TemplateManagerWebMvcActuatorProvider(Optional<ManagementServerProperties> managementServerProperties, Optional<WebEndpointProperties> webEndpointProperties, ServerProperties serverProperties, SpringDocConfigProperties springDocConfigProperties) {
            super(managementServerProperties, webEndpointProperties, serverProperties, springDocConfigProperties);
        }


        public static Tag getTag() {
            final Tag actuatorTag = new Tag();
            actuatorTag.setName(Constants.SPRINGDOC_ACTUATOR_TAG.toLowerCase());
            actuatorTag.setDescription(Constants.SPRINGDOC_ACTUATOR_DESCRIPTION);
            actuatorTag.setExternalDocs(
                    new ExternalDocumentation()
                            .url(Constants.SPRINGDOC_ACTUATOR_DOC_URL)
                            .description(Constants.SPRINGDOC_ACTUATOR_DOC_DESCRIPTION)
            );
            return actuatorTag;
        }

        @Override
        public Map getMethods() {
            return null;
        }


    }
}
