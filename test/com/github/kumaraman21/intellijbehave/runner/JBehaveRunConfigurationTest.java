package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.project.Project;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class JBehaveRunConfigurationTest {

    @Test
    public void createJBehaveRunConfiguration(){
        Project givenProject = mock(Project.class);
        ConfigurationFactory givenMockedConfigurationFactory = mock(ConfigurationFactory.class);
        String givenName = "test";

        new JBehaveRunConfiguration(givenName, givenProject, givenMockedConfigurationFactory);
    }
}
