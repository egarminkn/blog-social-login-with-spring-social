package se.callista.oauth.socialmedia.demo.config;

import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.odnoklassniki.connect.OdnoklassnikiConnectionFactory;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import se.callista.oauth.socialmedia.demo.dao.UsersDao;
import se.callista.oauth.socialmedia.demo.services.AccountConnectionSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

/**
 * Created by magnus on 18/08/14.
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsersDao usersDao;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(
            environment.getProperty("spring.social.facebook.appId"),
            environment.getProperty("spring.social.facebook.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(
            environment.getProperty("twitter.consumerKey"),
            environment.getProperty("twitter.consumerSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new LinkedInConnectionFactory(
            environment.getProperty("spring.social.linkedin.appId"),
            environment.getProperty("spring.social.linkedin.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(
            environment.getProperty("spring.social.google.appId"),
            environment.getProperty("spring.social.google.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new VKontakteConnectionFactory(
            environment.getProperty("spring.social.vkontakte.appId"),
            environment.getProperty("spring.social.vkontakte.appSecret")));
        connectionFactoryConfigurer.addConnectionFactory(new OdnoklassnikiConnectionFactory(
            environment.getProperty("spring.social.odnoklassniki.appId"),
            environment.getProperty("spring.social.odnoklassniki.appKeySecret"),
            environment.getProperty("spring.social.odnoklassniki.appKeyPublic")));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(new AccountConnectionSignUpService(usersDao));
        return repository;
    }
}
