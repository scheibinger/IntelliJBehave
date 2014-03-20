package com.github.kumaraman21.intellijbehave.runner;


import com.intellij.openapi.application.PluginPathManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import com.intellij.util.ui.UIUtil;
import org.junit.After;
import org.junit.Before;

import java.io.File;

/**
 * Created by rscheibinger on 3/20/14.
 */
public class ConfigurationsTest {
    private TempDirTestFixture myFixture;
    private IdeaProjectTestFixture myProjectFixture;

    @Before
    public void setUp() throws Exception {
        JavaTestFixtureFactory.getFixtureFactory();   // registers Java module fixture builder
        final IdeaTestFixtureFactory fixtureFactory = IdeaTestFixtureFactory.getFixtureFactory();
        final TestFixtureBuilder<IdeaProjectTestFixture> testFixtureBuilder = fixtureFactory.createFixtureBuilder();
        myFixture = fixtureFactory.createTempDirTestFixture();
        myFixture.setUp();

        FileUtil.copyDir(new File(PluginPathManager.getPluginHomePath("testng") + "/testData/runConfiguration/module1"),
                new File(myFixture.getTempDirPath()), false);

        myProjectFixture = testFixtureBuilder.getFixture();
        final JavaModuleFixtureBuilder javaModuleFixtureBuilder = testFixtureBuilder.addModule(JavaModuleFixtureBuilder.class);
        javaModuleFixtureBuilder.addContentRoot(myFixture.getTempDirPath()).addSourceRoot("src");
        myProjectFixture.setUp();

    }

    @After
    public void tearDown() throws Exception {
        UIUtil.invokeAndWaitIfNeeded(new Runnable() {
            @Override
            public void run() {
                try {
                    myProjectFixture.tearDown();
                    myProjectFixture = null;
                    myFixture.tearDown();
                    myFixture = null;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
