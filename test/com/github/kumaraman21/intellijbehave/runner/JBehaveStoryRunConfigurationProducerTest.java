package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by rscheibinger on 3/19/14.
 */
public class JBehaveStoryRunConfigurationProducerTest extends LightCodeInsightFixtureTestCase{

    public void testCreateRunConfigurationProducer() {
        JBehaveRunConfigurationType jBehaveRunConfigurationTypeMock = mock(JBehaveRunConfigurationType.class);
        JBehaveStoryRunConfigurationProducer jBehaveStoryRunConfigurationProducer = new JBehaveStoryRunConfigurationProducer(jBehaveRunConfigurationTypeMock);
    }

    public void testSetupConfigurationFromContext() throws Exception {

    }

    public void testIsConfigurationFromContext() throws Exception {

    }
}
