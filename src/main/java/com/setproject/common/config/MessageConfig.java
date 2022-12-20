package com.studysetting.common.config;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import net.rakugakibox.util.YamlResourceBundle;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
	@Value("${spring.messages.basename}")
	public String basename;

	@Value("${spring.messages.encoding}")
	public String encoding;


	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREAN);
		return localeResolver;
	}

	private static class YamlMessageSource extends ResourceBundleMessageSource {
		@Override
		protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
			return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
		}
	}

	@Bean
	public MessageSource messageSource() {
			YamlMessageSource yms = new YamlMessageSource();
			yms.setBasename(basename);
			yms.setDefaultEncoding(encoding);
			yms.setAlwaysUseMessageFormat(true);
			yms.setUseCodeAsDefaultMessage(true);
			yms.setFallbackToSystemLocale(true);
			return yms;
	}

}
