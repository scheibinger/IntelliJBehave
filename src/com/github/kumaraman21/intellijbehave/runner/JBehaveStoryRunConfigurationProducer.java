package com.github.kumaraman21.intellijbehave.runner;

import com.github.kumaraman21.intellijbehave.language.StoryFileType;
import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.junit.JavaRunConfigurationProducerBase;
import com.intellij.execution.junit2.info.LocationUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

public class JBehaveStoryRunConfigurationProducer extends JavaRunConfigurationProducerBase<JBehaveRunConfiguration> implements Cloneable {

    private static final String JBEHAVE_CLIENT_MAIN_CLASS = "org.jbehave.client.Main";

    public JBehaveStoryRunConfigurationProducer() {
        super(JBehaveRunConfigurationType.getInstance());
    }

    private String getConfigurationName(ConfigurationContext context) {
        final VirtualFile featureFile = getFileToRun(context);
        assert featureFile != null;
        return "Story: " + featureFile.getNameWithoutExtension();
    }

    @Nullable
    private VirtualFile getFileToRun(ConfigurationContext context) {
        final PsiElement element = context.getPsiLocation();
        if (element != null && element.getContainingFile().getFileType().equals(StoryFileType.STORY_FILE_TYPE)) {
            return element.getContainingFile().getVirtualFile();
        }

        return null;
    }

    @Override
    protected boolean setupConfigurationFromContext(JBehaveRunConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        if (!isConfigurationFromContext(configuration, context)) {
            return false;
        }
        configuration.setStoryPsiLocation(sourceElement.get());
        PsiFile containingFile = sourceElement.get().getContainingFile();
        configureMainClass(configuration, context);

        Project project = context.getProject();
        final Module module = ModuleUtilCore.findModuleForFile(containingFile.getVirtualFile(), project);
        configuration.setModule(module);
        return true;
    }

    private void configureMainClass(JBehaveRunConfiguration configuration, ConfigurationContext context) {
        String mainClassName = null;
        final Location location = context.getLocation();
        if (location != null) {
            if (LocationUtil.isJarAttached(location, JBEHAVE_CLIENT_MAIN_CLASS, new PsiDirectory[0])) {
                mainClassName = JBEHAVE_CLIENT_MAIN_CLASS;
            } else {
                throw new IllegalStateException("In order to run story, you should add jbehave-client to your classpath");
            }
        }

        configuration.MAIN_CLASS_NAME = mainClassName;
    }

    @Override
    public boolean isConfigurationFromContext(JBehaveRunConfiguration configuration, ConfigurationContext context) {
        if (getFileToRun(context) == null) {
            return false;
        }
        return true;
    }
}
