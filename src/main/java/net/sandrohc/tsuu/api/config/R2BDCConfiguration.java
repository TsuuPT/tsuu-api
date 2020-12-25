package net.sandrohc.tsuu.api.config;

import javax.sql.DataSource;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableR2dbcRepositories(basePackages = "net.sandrohc.tsuu.api.repositories")
public class R2BDCConfiguration extends AbstractR2dbcConfiguration {

	@Value("${spring.data.postgres.host}")      private String host;
	@Value("${spring.data.postgres.port:5432}") private int port;
	@Value("${spring.data.postgres.database}")  private String database;
	@Value("${spring.data.postgres.username}")  private String username;
	@Value("${spring.data.postgres.password}")  private String password;

	@Bean
	@NonNull
	@Override
	public ConnectionFactory connectionFactory() {
		return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
				.host(host)
				.port(port)
				.database(database)
				.username(username)
				.password(password)
				.build());
	}

	@Bean
	@FlywayDataSource
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://" + host + ":" + port + "/" + database)
				.username(username)
				.password(password)
				.build();
	}

}
