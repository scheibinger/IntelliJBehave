package com.github.kumaraman21.intellijbehave;

import com.github.kumaraman21.intellijbehave.runner.CreateStoryRunConfigurationFromContextTest;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public abstract class JBehaveCodeInsightFixtureTestCase extends LightCodeInsightFixtureTestCase {
    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        super.getProjectDescriptor();
        return new DefaultLightProjectDescriptor(){
            @Override
            public void configureModule(Module module, ModifiableRootModel model, ContentEntry contentEntry) {
                PsiTestUtil.addLibrary(module, model, "jbehave-client", getTestDataPath() + "/runner/lib/", "jbehave-client-1.0.jar");
            }
        };
    }

    @Override
    protected String getTestDataPath() {
        String jarPathForClass = PathManager.getJarPathForClass(CreateStoryRunConfigurationFromContextTest.class);
        File testDataPath = new File(jarPathForClass, "../../../testData");
        return testDataPath.getPath();
    }
}
