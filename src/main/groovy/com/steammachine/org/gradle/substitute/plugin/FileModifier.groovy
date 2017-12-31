package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import org.gradle.util.ConfigureUtil

import java.nio.file.Files
import java.nio.file.StandardCopyOption


/**
 * {@link com.steammachine.org.gradle.substitute.plugin.FileModifier}
 * com.steammachine.org.gradle.substitute.plugin.FileModifier
 */
@Api(value = State.MAINTAINED)
class FileModifier extends ConventionTask {

    List<ModificationRule> rules = []

    @TaskAction
    void perform() {

        project.sourceSets.each {
            sourceset ->
                sourceset.allSource.each {
                    File source ->
                        if (isModified(source)) {
                            modifyFile(source)
                        }
                }
        }

        rules.stream().filter { it in FinalInspection }.map { it as FinalInspection }.forEachOrdered {
            it.inspect()
        }
    }

    /**
     * Adds a simple rule,  that does not need any configuration
     *
     * @param clazz class to create instance [not null]
     */
    def <T extends ModificationRule> void rule(Class<T> clazz) {
        T instance = clazz.newInstance()
        rules.add(instance)
    }

    /**
     * Adds a rule class and performs its configuration
     *
     * @param clazz class to create instance [not null]
     */
    def <T extends ModificationRule> void rule(Class<T> clazz, Closure<T> config) {
        T instance = clazz.newInstance()
        ConfigureUtil.configureUsing(config).execute(instance)
        rules.add(instance)
    }

    boolean isModified(File file) {
        new BufferedReader(new InputStreamReader(new FileInputStream(file))).withCloseable {
            it.lines().anyMatch { line -> matchToAnyRule(line) }
        }
    }

    boolean matchToAnyRule(String line) {
        rules.stream().anyMatch { rule -> rule.lineMatches(line) }
    }

    void modifyFile(File file) {
        def temp = File.createTempFile("prefix", "suffix")
        temp.deleteOnExit()

        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp))).withCloseable {
            writer ->
                new BufferedReader(new InputStreamReader(new FileInputStream(file))).withCloseable {
                    it.lines().each {
                        line ->
                            String[] templine = [line]
                            if (matchToAnyRule(line)) {
                                rules.each { templine[0] = it.substitution(templine[0]) }
                            }
                            writer.writeLine(templine[0])
                    }
                }
        }
        Files.copy(temp.toPath(), file.toPath(),
                StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES)
    }

}
