package ru.kataproject.p_sm_airlines_1.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

// ClassPath, параметр, который указывает на расположение пользовательских классов и пакетов.
public class ResourceUtil {
    public static String getResourceAsString(String resourcePath) {
        if (resourcePath.startsWith("classpath:")) {
            resourcePath = resourcePath.replace("classpath:", "");
        }
        ClassPathResource resource = new ClassPathResource(resourcePath);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String getResourceAsString(Resource resource) {
        if (resource == null) {
            return "";
        }

        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
