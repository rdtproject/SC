package pl.pietro.standalone;

import java.util.List;

import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class JBehaveStoryRunner extends JUnitStories{

	private static final String STORIES_ROOT = "src/test-behave/resources";
	private static final String INCLUDE_STORIES = "**/*.story";
	private static final String EXCLUDE_STORIES = "**/exclude_*.story";
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(new MostUsefulConfiguration(), new BloomFilterBehavior());
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath(STORIES_ROOT), INCLUDE_STORIES, EXCLUDE_STORIES);
	}
		
}
