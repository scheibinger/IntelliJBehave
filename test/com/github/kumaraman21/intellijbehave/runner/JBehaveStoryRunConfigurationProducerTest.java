package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import static org.mockito.Mockito.mock;

public class JBehaveStoryRunConfigurationProducerTest extends LightCodeInsightFixtureTestCase {

    private JBehaveStoryRunConfigurationProducer givenStoryRunconfigurationProducer() {
        return new JBehaveStoryRunConfigurationProducer();
    }

    public void test_whenCreatingNewRunConfigurationProducer_thenItsCreated() {
        givenStoryRunconfigurationProducer();
    }

    public void test_whenSettingUpConfigurationFromContext_shouldSetupNewConfiguration() throws Exception {
        JBehaveRunConfiguration runConfigurationMock = mock(JBehaveRunConfiguration.class);
        ConfigurationContext configurationContext = mock(ConfigurationContext.class);
        Ref<PsiElement> sourceElement = (Ref<PsiElement>) mock(Ref.class);

        JBehaveStoryRunConfigurationProducer producer = givenStoryRunconfigurationProducer();

        producer.setupConfigurationFromContext(runConfigurationMock,configurationContext,sourceElement);
    }
}
