package com.github.kumaraman21.intellijbehave.runner;

import com.intellij.execution.JavaRunConfigurationExtensionManager;
import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.junit.JavaRunConfigurationProducerBase;
import com.intellij.execution.junit2.info.LocationUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;


public abstract class JBehaveAbstractRunConfigurationProducer extends JavaRunConfigurationProducerBase<ApplicationConfiguration> implements Cloneable{
    //todo
    private static final String JBEHAVE_MAIN_CLASS = "TODO";

    protected JBehaveAbstractRunConfigurationProducer() {
        super(JBehaveRunConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(ApplicationConfiguration applicationConfiguration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        //todo not implemented
        if(!(applicationConfiguration instanceof JBehaveRunConfiguration)) {
            return false;
        }
        JBehaveRunConfiguration configuration = (JBehaveRunConfiguration) applicationConfiguration;
        System.out.println("do some stuff");
        final VirtualFile virtualFile = getFileToRun(context);
        if (virtualFile == null) {
            return false;
        }

        final Project project = configuration.getProject();
        final PsiElement element = context.getPsiLocation();

        if (element == null) {
            return false;
        }

        final Module module = ModuleUtilCore.findModuleForFile(virtualFile, project);
        if (module == null) return false;

        String mainClassName = null;
        final Location location = context.getLocation();
        if (location != null) {
            if (LocationUtil.isJarAttached(location, JBEHAVE_MAIN_CLASS, new PsiDirectory[0])) {
                mainClassName = JBEHAVE_MAIN_CLASS;
            }
        }
        if (mainClassName == null) {
            return false;
        }

        final VirtualFile file = getFileToRun(context);
        if (file == null) {
            return false;
        }

        if (StringUtil.isEmpty(configuration.MAIN_CLASS_NAME)) {
            configuration.MAIN_CLASS_NAME = mainClassName;
        }

        configuration.setMyGeneratedName(getConfigurationName(context));

        setupConfigurationModule(context, configuration);
        JavaRunConfigurationExtensionManager.getInstance().extendCreatedConfiguration(configuration, location);
        return true;
    }

    protected abstract String getConfigurationName(ConfigurationContext context);


    @Nullable
    protected abstract VirtualFile getFileToRun(ConfigurationContext context);

    @Override
    public boolean isConfigurationFromContext(ApplicationConfiguration configuration, ConfigurationContext context) {
        //todo not implemented
        return false;
    }

}
