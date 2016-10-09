package pl.pietro.backend.behave;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class JBehaveStoryRunner extends JUnitStories {

	private static final String STORIES_ROOT = "./src/test-behave";
	private static final String INCLUDE_STORIES = "**/*.story"; 
	private static final String EXCLUDE_STORIES = "**/exclude_*.story";
	
	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath(STORIES_ROOT), INCLUDE_STORIES, EXCLUDE_STORIES);		
	}
	
	@Override
	public Configuration configuration() {
		try {
			return new MostUsefulConfiguration()
					.useStoryLoader(new LoadFromRelativeFile(new java.io.File(STORIES_ROOT).toURI().toURL(),
							new LoadFromRelativeFile.StoryFilePath(".",  ".")))
					.useStoryReporterBuilder(new StoryReporterBuilder()
							.withFormats(Format.XML, Format.IDE_CONSOLE, Format.CONSOLE, Format.HTML, Format.TXT));
		} catch (MalformedURLException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), createContext());
	}
	
	private ApplicationContext createContext() {
		return new SpringApplicationContextFactory("pl.pietro.backend.behave.BehaveSpringConfig",
				"pl.pietro.backend.MainAppSpringConfig").createApplicationContext();
	}
}
