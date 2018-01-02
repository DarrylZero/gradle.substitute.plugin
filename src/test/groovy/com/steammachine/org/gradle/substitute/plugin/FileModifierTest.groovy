package com.steammachine.org.gradle.substitute.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test
import com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DirectSubstitution

import java.nio.file.Files

class FileModifierTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.FileModifier",
                FileModifier.class.name);
    }

    @Test
    void testFileModifierPerformance() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply SubstitutePlugin
        def modifier = project.findProperty('modifysources') as FileModifier

        modifier.rule(DirectSubstitution) {
            find = '#TEMPLATE'
            substitute = 'something'

        }

        def dir = File.createTempDir('prefix', 'suffix')
        def file = new File(dir, 'temp.txt')
        dir.deleteOnExit()
        file.deleteOnExit()

        FileModifierTest.class.getResourceAsStream('resource/resource.txt').withCloseable {
            Files.copy(it, file.toPath())
        }
//        file = new File(dir, 'temp.txt')
//        file.deleteOnExit()

        modifier.modifyFile(file)

        new BufferedReader(new InputStreamReader(new FileInputStream(file))).withCloseable {
            reader ->
                String line = reader.readLine()
                Assert.assertEquals('something', line)
        }
    }

}
