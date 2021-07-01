package monkeys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MonkeysCreationTest {

	@Test
	void contextLoads() {
	}

	@Test
	void checkNewMonkey() {
		assertThat().isNotNull();
	}

}
