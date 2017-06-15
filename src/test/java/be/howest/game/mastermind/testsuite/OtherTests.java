package be.howest.game.mastermind.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import be.howest.game.mastermind.commons.ColorTest;
import be.howest.game.mastermind.services.ColorServiceTest;
import be.howest.game.mastermind.services.TemplateServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ColorTest.class,
	ColorServiceTest.class,
	TemplateServiceTest.class
})
public class OtherTests {

}
