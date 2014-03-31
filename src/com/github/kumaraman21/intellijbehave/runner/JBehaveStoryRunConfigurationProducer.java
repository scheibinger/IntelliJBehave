package com.github.kumaraman21.intellijbehave.runner;

import com.github.kumaraman21.intellijbehave.language.StoryFileType;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.junit.JavaRunConfigurationProducerBase;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public class JBehaveStoryRunConfigurationProducer extends JavaRunConfigurationProducerBase<JBehaveRunConfiguration> implements Cloneable{

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
        System.out.println("Configure to run" + context.getPsiLocation().getContainingFile().getVirtualFile().getPath()); 
        configuration.setStoryPsiLocation(sourceElement.get());
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(JBehaveRunConfiguration configuration, ConfigurationContext context) {
        return true;
    }
}
