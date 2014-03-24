package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by rscheibinger on 3/19/14.
 */
public class JBehaveAbstractRunConfigurationProducerTest extends LightCodeInsightFixtureTestCase {

    private JBehaveAbstractRunConfigurationProducer givenStoryRunconfigurationProducer(
            JBehaveRunConfigurationType jBehaveRunConfigurationTypeMock) {

        return new JBehaveStoryRunConfigurationProducerImpl(jBehaveRunConfigurationTypeMock);
    }

    public void test_whenCreatingNewRunConfigurationProducer_thenItsCreated() {
        JBehaveRunConfigurationType jBehaveRunConfigurationTypeMock = mock(JBehaveRunConfigurationType.class);
        givenStoryRunconfigurationProducer(jBehaveRunConfigurationTypeMock);
    }

    public void test_whenSettingUpConfigurationFromContext() throws Exception {
        JBehaveRunConfigurationType jBehaveRunConfigurationTypeMock = mock(JBehaveRunConfigurationType.class);
        JBehaveRunConfiguration runConfigurationMock = mock(JBehaveRunConfiguration.class);
        ConfigurationContext configurationContext = mock(ConfigurationContext.class);
        Ref<PsiElement> sourceElement = (Ref<PsiElement>) mock(Ref.class);

        JBehaveAbstractRunConfigurationProducer producer =
                givenStoryRunconfigurationProducer(jBehaveRunConfigurationTypeMock);

        producer.setupConfigurationFromContext(runConfigurationMock,configurationContext,sourceElement);


    }

    public void testIsConfigurationFromContext() throws Exception {

    }
}
