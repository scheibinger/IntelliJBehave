package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.junit.JavaRunConfigurationProducerBase;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;

import javax.naming.OperationNotSupportedException;

public class JBehaveStoryRunConfigurationProducer extends JavaRunConfigurationProducerBase<ApplicationConfiguration> implements Cloneable{
    protected JBehaveStoryRunConfigurationProducer(ConfigurationType configurationType) {
        super(JBehaveRunConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(ApplicationConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        //todo not implemented
        System.out.println("do some stuff");
        return false;
    }

    @Override
    public boolean isConfigurationFromContext(ApplicationConfiguration configuration, ConfigurationContext context) {
        //todo not implemented
        return false;
    }

}
