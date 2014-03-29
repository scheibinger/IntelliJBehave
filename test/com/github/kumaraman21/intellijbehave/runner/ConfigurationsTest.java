package com.github.kumaraman21.intellijbehave.runner;


import com.intellij.execution.Location;
import com.intellij.execution.PsiLocation;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.ConfigurationFromContext;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.MapDataContext;
import com.intellij.testFramework.TestDataPath;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static org.fest.assertions.api.Assertions.assertThat;

@TestDataPath("$CONTENT_ROOT/testData/runner/module1")
public class ConfigurationsTest extends LightCodeInsightFixtureTestCase {

    private final String TEST_STORY_RELATIVE_PATH = "stories/test.story";

    public void setUp() throws Exception {
        super.setUp();
        myFixture.copyDirectoryToProject("runner/stories", "stories");


        //FileUtil.copyDir(fromDir, toDir, false);
        System.out.println("asfd");
    }

    @Override
    protected String getTestDataPath() {
        String jarPathForClass = PathManager.getJarPathForClass(ConfigurationsTest.class);
        File testDataPath = new File(jarPathForClass, "../../../testData");
        return testDataPath.getPath();
    }

    public void test_whenCreatingRunConfigurationFromContext() {
        System.out.println("test stub");
    }

    public void shouldCreateJBehaveRunConfigurationFromContext() {
        final Project project = myFixture.getProject();
        final PsiFile storyPsiFile = null;//findStoryFile(project);
        final JBehaveStoryRunConfigurationProducer producer = new JBehaveStoryRunConfigurationProducer();

        final MapDataContext dataContext = new MapDataContext();

        dataContext.put(CommonDataKeys.PROJECT, project);
        dataContext.put(Location.DATA_KEY, PsiLocation.fromPsiElement(storyPsiFile));

        final ConfigurationFromContext fromContext = producer.createConfigurationFromContext(ConfigurationContext.getFromContext(dataContext));
        assert fromContext != null;
        final RunnerAndConfigurationSettings config = fromContext.getConfigurationSettings();
        final RunConfiguration runConfiguration = config.getConfiguration();
        // Assert.assertTrue(runConfiguration instanceof TestNGConfiguration);

        // TestNGConfigurationType t = (TestNGConfigurationType)runConfiguration.getType();
        // Assert.assertTrue(t.isConfigurationByLocation(runConfiguration, new PsiLocation(project, psiClass)));


    }

    public void test_shouldFindStoryFileInTheProject() {
        final Project project = myFixture.getProject();
        final PsiFile storyPsiFile = findStoryFileInTheProject(project, TEST_STORY_RELATIVE_PATH);

        assertThat(storyPsiFile).isNotNull();
    }

    private PsiFile findStoryFileInTheProject(Project project, @NotNull String relativePath) {
        VirtualFile virtualFile = myFixture.findFileInTempDir(relativePath);
        return getPsiManager().findFile(virtualFile);

    }

}
