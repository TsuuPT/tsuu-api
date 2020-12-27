package net.sandrohc.tsuu.api;

import com.oembedler.moon.graphql.boot.GraphQLWebAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"graphql.servlet.websocket.enabled=false",
		"spring.flyway.enabled=false"
})
@EnableAutoConfiguration(exclude = GraphQLWebAutoConfiguration.class)
class TsuuApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
