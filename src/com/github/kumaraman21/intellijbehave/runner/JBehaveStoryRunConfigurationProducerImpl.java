package com.github.kumaraman21.intellijbehave.runner;

import com.github.kumaraman21.intellijbehave.language.StoryFileType;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by rscheibinger on 3/20/14.
 */
public class JBehaveStoryRunConfigurationProducerImpl extends JBehaveAbstractRunConfigurationProducer {
    protected JBehaveStoryRunConfigurationProducerImpl(ConfigurationType configurationType) {
        super(configurationType);
    }

    @Override
    protected String getConfigurationName(ConfigurationContext context) {
        final VirtualFile featureFile = getFileToRun(context);
        assert featureFile != null;
        return "Story: " + featureFile.getNameWithoutExtension();
    }

    @Nullable
    @Override
    protected VirtualFile getFileToRun(ConfigurationContext context) {
        final PsiElement element = context.getPsiLocation();
        if (element != null && element.getContainingFile().getFileType().equals(StoryFileType.STORY_FILE_TYPE)) {
            return element.getContainingFile().getVirtualFile();
        }

        return null;
    }

}
