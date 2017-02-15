package org.verygroup.sociallogin.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInController;
import org.verygroup.sociallogin.dao.UsersDao;
import org.verygroup.sociallogin.services.AccountConnectionSignUpService;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.google.security.GoogleAuthenticationService;
import org.springframework.social.linkedin.security.LinkedInAuthenticationService;
import org.springframework.social.odnoklassniki.security.OdnoklassnikiAuthenticationService;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.twitter.security.TwitterAuthenticationService;
import org.springframework.social.vkontakte.security.VKontakteAuthenticationService;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.odnoklassniki.connect.OdnoklassnikiConnectionFactory;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;

import javax.sql.DataSource;

@Configuration
//@EnableSocial
@PropertySource("classpath:social/social.properties")
public class SocialConfig /*implements SocialConfigurer*/ {

    // --------------------------------------
    // Так было:
    // --------------------------------------

//    @Autowired
//    private DataSource dataSource;

//    @Autowired
//    private UsersDao usersDao;

//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
//        connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(
//                environment.getProperty("spring.social.facebook.appId"),
//                environment.getProperty("spring.social.facebook.appSecret")));
//        connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(
//                environment.getProperty("spring.social.twitter.consumerKey"),
//                environment.getProperty("spring.social.twitter.consumerSecret")));
//        connectionFactoryConfigurer.addConnectionFactory(new LinkedInConnectionFactory(
//                environment.getProperty("spring.social.linkedin.appId"),
//                environment.getProperty("spring.social.linkedin.appSecret")));
//        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(
//                environment.getProperty("spring.social.google.appId"),
//                environment.getProperty("spring.social.google.appSecret")));
//        connectionFactoryConfigurer.addConnectionFactory(new VKontakteConnectionFactory(
//                environment.getProperty("spring.social.vkontakte.appId"),
//                environment.getProperty("spring.social.vkontakte.appSecret")));
//        connectionFactoryConfigurer.addConnectionFactory(new OdnoklassnikiConnectionFactory(
//                environment.getProperty("spring.social.odnoklassniki.appId"),
//                environment.getProperty("spring.social.odnoklassniki.appKeySecret"),
//                environment.getProperty("spring.social.odnoklassniki.appKeyPublic")));
//    }

//    @Override
//    public UserIdSource getUserIdSource() {
//        return new AuthenticationNameUserIdSource();
//    }

//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//        repository.setConnectionSignUp(new AccountConnectionSignUpService(usersDao));
//        return repository;
//    }

    // --------------------------------------
    // Так стало:
    // --------------------------------------

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator(Environment environment) {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(
                environment.getProperty("spring.social.facebook.appId"),
                environment.getProperty("spring.social.facebook.appSecret")));
        registry.addConnectionFactory(new TwitterConnectionFactory(
                environment.getProperty("spring.social.twitter.consumerKey"),
                environment.getProperty("spring.social.twitter.consumerSecret")));
        registry.addConnectionFactory(new LinkedInConnectionFactory(
                environment.getProperty("spring.social.linkedin.appId"),
                environment.getProperty("spring.social.linkedin.appSecret")));
        registry.addConnectionFactory(new GoogleConnectionFactory(
                environment.getProperty("spring.social.google.appId"),
                environment.getProperty("spring.social.google.appSecret")));
        registry.addConnectionFactory(new VKontakteConnectionFactory(
                environment.getProperty("spring.social.vkontakte.appId"),
                environment.getProperty("spring.social.vkontakte.appSecret")));
        registry.addConnectionFactory(new OdnoklassnikiConnectionFactory(
                environment.getProperty("spring.social.odnoklassniki.appId"),
                environment.getProperty("spring.social.odnoklassniki.appKeySecret"),
                environment.getProperty("spring.social.odnoklassniki.appKeyPublic")));
        return registry;
    }

    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }

    @Bean
    @Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository usersConnectionRepository(UsersDao usersDao, DataSource dataSource, ConnectionFactoryLocator connectionFactoryLocator, TextEncryptor textEncryptor) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, textEncryptor);
        repository.setConnectionSignUp(new AccountConnectionSignUpService(usersDao));
        return repository;
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository(UsersConnectionRepository usersConnectionRepository) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }
        return usersConnectionRepository.createConnectionRepository(authentication.getName());
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository, Environment environment) {
        ConnectController controller = new ConnectController(connectionFactoryLocator, connectionRepository);
//        controller.setApplicationUrl(environment.getProperty("application.url"));
        return controller;
    }

    @Bean
    public SocialAuthenticationServiceLocator socialAuthenticationServiceLocator(Environment environment) {
        SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
        registry.addAuthenticationService(new FacebookAuthenticationService(
                environment.getProperty("spring.social.facebook.appId"),
                environment.getProperty("spring.social.facebook.appSecret")));
        registry.addAuthenticationService(new TwitterAuthenticationService(
                environment.getProperty("spring.social.twitter.consumerKey"),
                environment.getProperty("spring.social.twitter.consumerSecret")));
        registry.addAuthenticationService(new LinkedInAuthenticationService(
                environment.getProperty("spring.social.linkedin.appId"),
                environment.getProperty("spring.social.linkedin.appSecret")));
        registry.addAuthenticationService(new GoogleAuthenticationService(
                environment.getProperty("spring.social.google.appId"),
                environment.getProperty("spring.social.google.appSecret")));
        registry.addAuthenticationService(new VKontakteAuthenticationService(
                environment.getProperty("spring.social.vkontakte.appId"),
                environment.getProperty("spring.social.vkontakte.appSecret")));
        registry.addAuthenticationService(new OdnoklassnikiAuthenticationService(
                environment.getProperty("spring.social.odnoklassniki.appId"),
                environment.getProperty("spring.social.odnoklassniki.appKeySecret"),
                environment.getProperty("spring.social.odnoklassniki.appKeyPublic")));
        return registry;
    }

}