package com.steammachine.org.gradle.substitute.plugin

import org.gradle.api.Project
import org.gradle.api.internal.project.DefaultProject
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test

class SubstitutePluginTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals('com.steammachine.org.gradle.substitute.plugin.SubstitutePlugin',
                SubstitutePlugin.class.name);
    }

    @Test
    void testTaskNameIntegrity() {
        Assert.assertEquals('modifysources', SubstitutePlugin.TASK_NAME);
    }

    @Test
    void testTaskGroupNameIntegrity() {
        Assert.assertEquals('modification', SubstitutePlugin.MODIFICATION);
    }

    @Test
    void testWithDefaultProject() {
        Project project = ProjectBuilder.builder().build()
        Assert.assertNull(project.findProperty('modifysources'));
        project.pluginManager.apply('com.steammachine.org.gradle.substitute.plugin')
        Assert.assertTrue(project.findProperty('modifysources') in FileModifier);
    }


    @Test
    void testWithDefaultProject20() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply('com.steammachine.org.gradle.substitute.plugin')
        Assert.assertEquals('modification', (project.findProperty('modifysources') as FileModifier).group)
    }

    @Test
    void testWithDefaultProject30() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply('com.steammachine.org.gradle.substitute.plugin')
        Assert.assertEquals('modification', (project.findProperty('modifysources') as FileModifier).group)
    }


}
