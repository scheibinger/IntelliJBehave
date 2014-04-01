package com.github.kumaraman21.intellijbehave.runner;


import com.intellij.execution.Location;
import com.intellij.execution.PsiLocation;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.ConfigurationFromContext;
import com.intellij.execution.configurations.ConfigurationType;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@TestDataPath("$CONTENT_ROOT/testData/runner/module1")
public class CreateStoryRunConfigurationFromContextTest extends LightCodeInsightFixtureTestCase {

    private final String TEST_STORY_RELATIVE_PATH = "stories/test.story";
    private final String RANDOM_FILE_RELATIVE_PATH = "stories/test.not_story";

    public void setUp() throws Exception {
        super.setUp();
        myFixture.copyDirectoryToProject("runner/stories", "stories");
    }

    @Override
    protected String getTestDataPath() {
        String jarPathForClass = PathManager.getJarPathForClass(CreateStoryRunConfigurationFromContextTest.class);
        File testDataPath = new File(jarPathForClass, "../../../testData");
        return testDataPath.getPath();
    }

    public void test_whenCreatingJBehaveRunConfigurationFromContext_shouldCreateAndSetUpJBehaveRunConfiguration() {
        JBehaveStoryRunConfigurationProducer jBehaveStoryRunConfigurationProducer = givenJBehaveStoryRunConfigurationProducer();

        final MapDataContext dataContext = givenContextForAFile(TEST_STORY_RELATIVE_PATH);

        ConfigurationContext fromContext = ConfigurationContext.getFromContext(dataContext);

        final ConfigurationFromContext configurationFromContext = jBehaveStoryRunConfigurationProducer.createConfigurationFromContext(fromContext);
        assertThat(configurationFromContext).isNotNull();
        ConfigurationType configurationType = configurationFromContext.getConfigurationType();
        assertThat(configurationType).isInstanceOf(JBehaveRunConfigurationType.class);

        //todo: assertions for set up
    }

    private MapDataContext givenContextForAFile(String relativePathToFile) {
        final Project project = myFixture.getProject();
        final PsiFile storyPsiFile = findStoryFileInTheProject(project, relativePathToFile);

        final MapDataContext dataContext = new MapDataContext();

        dataContext.put(CommonDataKeys.PROJECT, project);
        dataContext.put(Location.DATA_KEY, PsiLocation.fromPsiElement(storyPsiFile));
        return dataContext;
    }

    public void test_whenSettingUpConfigurationFromContext_shouldReuseExistingRunConfiguration(){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private JBehaveStoryRunConfigurationProducer givenJBehaveStoryRunConfigurationProducer() {
        return new JBehaveStoryRunConfigurationProducer();
    }

    public void test_givenJBehaveStoryRunConfigurationProducer_shouldBeTypeOfJBehaveRunConfigurationType(){
        JBehaveStoryRunConfigurationProducer jBehaveStoryRunConfigurationProducer = givenJBehaveStoryRunConfigurationProducer();
        String displayName = jBehaveStoryRunConfigurationProducer.getConfigurationType().getDisplayName();
        assertThat(displayName).isEqualTo("JBehave");
    }

    public void test_shouldFindStoryFileInTheProject() {
        final Project project = myFixture.getProject();
        final PsiFile storyPsiFile = findStoryFileInTheProject(project, TEST_STORY_RELATIVE_PATH);

        assertThat(storyPsiFile).isNotNull();
    }

    public void test_whenCreatingConfigurationFromContext_shouldCheckIfConfigurationIsFromContext(){
        JBehaveStoryRunConfigurationProducer storyRunConfigurationProducer = givenJBehaveStoryRunConfigurationProducer();
        JBehaveStoryRunConfigurationProducer configurationProducerSpy = spy(storyRunConfigurationProducer);
        final MapDataContext dataContext = givenContextForAFile(RANDOM_FILE_RELATIVE_PATH);
        ConfigurationContext fromContext = ConfigurationContext.getFromContext(dataContext);
        final ConfigurationFromContext configurationFromContext = configurationProducerSpy.createConfigurationFromContext(fromContext);
        verify(configurationProducerSpy, times(1)).isConfigurationFromContext(any(JBehaveRunConfiguration.class),any(ConfigurationContext.class));
    }

    private PsiFile findStoryFileInTheProject(Project project, @NotNull String relativePath) {
        VirtualFile virtualFile = myFixture.findFileInTempDir(relativePath);
        return getPsiManager().findFile(virtualFile);
    }
}
