package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.*;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.testframework.sm.SMTestRunnerConnectionUtil;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.execution.util.JavaParametersUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class JBehaveRunConfiguration extends ApplicationConfiguration {

    private PsiElement storyPsiLocation;

    public JBehaveRunConfiguration(String name, Project project, ConfigurationFactory configurationFactory) {
        super(name, project, configurationFactory);
    }

    public void setStoryPsiLocation(PsiElement storyPsiLocation) {
        this.storyPsiLocation = storyPsiLocation;
    }

    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return new JavaApplicationCommandLineState(JBehaveRunConfiguration.this, env) {

            @Override
            protected JavaParameters createJavaParameters() throws ExecutionException {
                final JavaParameters params = new JavaParameters();
                final JavaRunConfigurationModule module = getConfigurationModule();

                final int classPathType = JavaParameters.JDK_AND_CLASSES_AND_TESTS;
                final String jreHome = JBehaveRunConfiguration.this.ALTERNATIVE_JRE_PATH_ENABLED ? ALTERNATIVE_JRE_PATH : null;
                JavaParametersUtil.configureModule(module, params, classPathType, jreHome);
                JavaParametersUtil.configureConfiguration(params, JBehaveRunConfiguration.this);

                // String path = getSMRunnerPath();
                //params.getClassPath().add(path);

                params.setMainClass(MAIN_CLASS_NAME);
                for (RunConfigurationExtension ext : Extensions.getExtensions(RunConfigurationExtension.EP_NAME)) {
                    ext.updateJavaParameters(JBehaveRunConfiguration.this, params, getRunnerSettings());
                }

                //params.getProgramParametersList().addParametersString("\"" + myFilePath + "\"");
                return params;
            }

            @Nullable
            private ConsoleView createConsole(@NotNull final Executor executor, ProcessHandler processHandler) throws ExecutionException {
                // console view
                final ConsoleView testRunnerConsole;

                final String testFrameworkName = "jbehave";
                final JBehaveRunConfiguration runConfiguration = JBehaveRunConfiguration.this;
                final SMTRunnerConsoleProperties consoleProperties = new SMTRunnerConsoleProperties(runConfiguration, testFrameworkName, executor);

                testRunnerConsole = SMTestRunnerConnectionUtil.createAndAttachConsole(testFrameworkName, processHandler, consoleProperties,
                        getEnvironment());

                return testRunnerConsole;
            }

            @NotNull
            @Override
            public ExecutionResult execute(@NotNull Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
                final ProcessHandler processHandler = startProcess();
                final ConsoleView console = createConsole(executor, processHandler);
                return new DefaultExecutionResult(console, processHandler, createActions(console, processHandler, executor));
            }
        };
    }
}
