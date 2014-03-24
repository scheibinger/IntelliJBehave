package com.github.kumaraman21.intellijbehave.runner;


import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import java.io.File;

public class ConfigurationsTest extends LightCodeInsightFixtureTestCase{

    public void setUp() throws Exception {
        super.setUp();
        File fromDir = new File(getTestDataPath() + "/testData/runner/module1");
        File toDir = new File(myFixture.getTempDirPath());

        FileUtil.copyDir(fromDir, toDir, false);
    }

    @Override
    protected String getTestDataPath() {
        String jarPathForClass = PathManager.getJarPathForClass(ConfigurationsTest.class);
        File testDataPath = new File(jarPathForClass, "../../..");
        return testDataPath.getPath();
    }

    public void test_whenCreatingRunConfigurationFromContext() {
        System.out.println("test stub");

    }


}
