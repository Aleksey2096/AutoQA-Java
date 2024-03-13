package pl.traineeship.autoqa.lesson14.task2.util;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.CustomAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRailListener implements ITestListener, ISuiteListener {
    private static final TestRail TEST_RAIL = TestRail.builder("https://autoqa96.testrail.io/",
            "test@gmail.com", "testTEST1!").applicationName("AQA-10").build();

    private Map<Integer, Integer> testRailResults = new HashMap<>();

    @Override
    public void onTestSuccess(final ITestResult result) {
        putTestResult(result, 1);
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        putTestResult(result, 5);
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        putTestResult(result, 2);
    }

    @Override
    public void onFinish(final ISuite suite) {
        Run run = TEST_RAIL.runs().add(1, new Run().setSuiteId(1).setName("TestRun for AQA10"))
                .execute();
        List<ResultField> resultFields = TEST_RAIL.resultFields().list().execute();
        // 1 -passed, 2 - blocked, 4 - retest, 5 - failed
        for (Map.Entry<Integer, Integer> entry : testRailResults.entrySet()) {
            TEST_RAIL.results().addForCase(run.getId(), entry.getKey(), new Result().setStatusId(
                    entry.getValue()), resultFields).execute();
        }
        TEST_RAIL.runs().close(run.getId()).execute();
    }

    private void putTestResult(ITestResult result, int status) {
        CustomAttribute[] attributes = result.getMethod().getAttributes();
        for (CustomAttribute attribute : attributes) {
            if (attribute.name().equals("testRailId")) {
                testRailResults.put(Integer.valueOf(attribute.values()[0]), status);
                return;
            }
        }
    }
}
