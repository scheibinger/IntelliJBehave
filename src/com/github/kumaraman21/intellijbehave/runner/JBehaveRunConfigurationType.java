package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class JBehaveRunConfigurationType extends ApplicationConfigurationType {


    private static final String ID = "JBehaveRunConfiguration";
    private final ConfigurationFactory jBehaveRunConfigurationFactory;

    public JBehaveRunConfigurationType() {
        jBehaveRunConfigurationFactory = new JBehaveRunConfigurationFactory(this);
    }

    public static JBehaveRunConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(JBehaveRunConfigurationType.class);
    }

    @Override
    public String getDisplayName() {
        return "JBehave";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "JBehave Test Run Configuration";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{jBehaveRunConfigurationFactory};
    }

    @NotNull
    @Override
    public String getId() {
        return ID;
    }

    private static class JBehaveRunConfigurationFactory extends ConfigurationFactory{

        protected JBehaveRunConfigurationFactory(@NotNull ConfigurationType type) {
            super(type);
        }

        @Override
        public RunConfiguration createTemplateConfiguration(Project project) {
            return new JBehaveRunConfiguration("JBehave",project, this);
        }
    }
}
