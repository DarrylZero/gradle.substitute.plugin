package com.steammachine.org.gradle.substitute.plugin

import org.gradle.api.Project
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

    @Test
    void testWithDefaultProject40() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply('com.steammachine.org.gradle.substitute.plugin')
        def modifier = project.findProperty('modifysources') as FileModifier
        Assert.assertEquals('modification', modifier.group)
        modifier.rule(Object) /* adding simple object with no-arg constructor is possible */
    }

    class Simple {
        String line

        void setLine(String line) {
            this.line = line
        }
    }

    @Test
    void testWithDefaultProject50() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply('com.steammachine.org.gradle.substitute.plugin')
        def modifier = project.findProperty('modifysources') as FileModifier
        Assert.assertEquals('modification', modifier.group)
        /* adding simple object of type Simple with no-arg constructor is possible - as well */
        modifier.rule(Simple) {
            line = '42'
        }
    }


}
