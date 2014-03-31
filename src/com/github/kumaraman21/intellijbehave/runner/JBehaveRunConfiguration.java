package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;


public class JBehaveRunConfiguration extends ApplicationConfiguration {

    private PsiElement storyPsiLocation;

    public JBehaveRunConfiguration(String name, Project project, ConfigurationFactory configurationFactory) {
        super(name, project, configurationFactory);
    }

    public void setStoryPsiLocation(PsiElement storyPsiLocation) {
        this.storyPsiLocation = storyPsiLocation;
    }
}
