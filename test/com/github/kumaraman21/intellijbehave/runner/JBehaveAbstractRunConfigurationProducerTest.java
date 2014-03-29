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

    private JBehaveAbstractRunConfigurationProducer givenStoryRunconfigurationProducer() {

        return new JBehaveStoryRunConfigurationProducer();
    }

    public void test_whenCreatingNewRunConfigurationProducer_thenItsCreated() {
        givenStoryRunconfigurationProducer();
    }

    public void test_whenSettingUpConfigurationFromContext() throws Exception {
        JBehaveRunConfiguration runConfigurationMock = mock(JBehaveRunConfiguration.class);
        ConfigurationContext configurationContext = mock(ConfigurationContext.class);
        Ref<PsiElement> sourceElement = (Ref<PsiElement>) mock(Ref.class);

        JBehaveAbstractRunConfigurationProducer producer = givenStoryRunconfigurationProducer();

        producer.setupConfigurationFromContext(runConfigurationMock,configurationContext,sourceElement);
    }
}
