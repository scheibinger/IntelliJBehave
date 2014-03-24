package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.project.Project;


public class JBehaveRunConfiguration extends ApplicationConfiguration {

    public String getMyGeneratedName() {
        return myGeneratedName;
    }

    public void setMyGeneratedName(String myGeneratedName) {
        this.myGeneratedName = myGeneratedName;
    }

    private String myGeneratedName;

    public JBehaveRunConfiguration(String name, Project project, ConfigurationFactory configurationFactory) {
        super(name, project, configurationFactory);
    }
}
