package be.howest.game.mastermind.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import be.howest.game.mastermind.commons.ColorTest;
import be.howest.game.mastermind.services.ColorServiceTest;
import be.howest.game.mastermind.services.TemplateServiceTest;
import be.howest.game.mastermind.util.ToolsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ColorTest.class,
	ColorServiceTest.class,
	TemplateServiceTest.class,
	ToolsTest.class
})
public class OtherTests {

}
