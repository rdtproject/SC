package pl.pietro.backend;

import org.junit.Rule;
import org.junit.Test;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "map-types-benchmark-barchart")
@BenchmarkOptions(warmupRounds = 100, benchmarkRounds = 100, callgc = false, clock = Clock.NANO_TIME)
public class TestBenchmark {

	@Rule
	public BenchmarkRule benchmarkRun = new BenchmarkRule();
	
//	@BeforeClass
//	public void launchBenchmark() throws Exception {
//		
//	}

//	public static Iterable<Object[]> data() throws Exception {
//		return Arrays.asList(
//				new Object[][]{
//					{1000, 5},
//					{1000, 10},
//					{1000, 15}
//				}
//				);
//	}

//	@Parameter(value = 0)
//	public int numberOfWords;
	
	@Test
	public void shouldVerifyPerformance() {
		double i = 1;
		for (int j=0; j< 1000000 ; j++) {
			i = (i * 2.2) / 3;
		}
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 4)
	public void shouldVerifyPerformance2() {
		double i = 1;
		for (int j=0; j< 1000000 ; j++) {
			i = (i * 2.2) / 3;
		}
	}
	
}
