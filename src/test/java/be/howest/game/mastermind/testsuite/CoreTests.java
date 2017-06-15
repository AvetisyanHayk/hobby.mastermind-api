package be.howest.game.mastermind.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import be.howest.game.mastermind.FeedbackTest;
import be.howest.game.mastermind.HistoryTest;
import be.howest.game.mastermind.MasterMindManagerTest;
import be.howest.game.mastermind.TemplateTest;
import be.howest.game.mastermind.MasterMindTest;
import be.howest.game.mastermind.SecretTest;
import be.howest.game.mastermind.ToolsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FeedbackTest.class,
	HistoryTest.class,
	MasterMindManagerTest.class,
	MasterMindTest.class,
	SecretTest.class,
	TemplateTest.class,
	ToolsTest.class
})
public class CoreTests {

}
