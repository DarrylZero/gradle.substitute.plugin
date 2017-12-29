package com.steammachine.org.gradle.substitute.plugin

import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import org.gradle.util.ConfigureUtil

import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class FileModifier extends ConventionTask {

    List<ModificationRule> rules = []

    @TaskAction
    void perform() {


        project.sourceSets.each {
            sourceset ->
                println "$sourceset "

                println sourceset.allSource.class.name
                sourceset.allSource.each {
                    File source ->
                        println "source file  $source   --> $source.absolutePath" + source.class

                        if (isModified(source)) {
                            println "about ot be modified !!! $source"
                            modifyFile(source)
                        }
                }
        }
    }

    def <T extends ModificationRule> void addRule(Class<T> clazz, Closure<T> config) {
        T instance = clazz.newInstance()
        ConfigureUtil.configureUsing(config).execute(instance)
        rules.add(instance)
    }

    /*  */

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
        Files.copy(temp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES)
    }


}