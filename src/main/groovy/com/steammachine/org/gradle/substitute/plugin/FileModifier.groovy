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

    List<Object> rules = []

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
    def <T> void rule(Class<T> clazz) {
        rules.add(clazz.newInstance())
    }

    /**
     * Adds a rule class and performs its configuration
     *
     * @param clazz class to create instance [not null]
     */
    def <T> void rule(Class<T> clazz, Closure<T> config) {
        ConfigureUtil.configureUsing(config).execute(rule(clazz))
    }

    boolean isModified(File file) {
        new BufferedReader(new InputStreamReader(new FileInputStream(file))).withCloseable {
            reader ->
                def line
                int lineNo = 0
                boolean result = true
                while ((line = reader.readLine()) != null) {
                    result = result && matchToAnyRule(line, file, lineNo++)
                }
        }
    }

    boolean matchToAnyRule(String line, File file, int lineNo) {
        rules.stream().anyMatch { lineMatches(it, line, file, lineNo) }
    }

    void modifyFile(File file) {
        def temp = File.createTempFile("prefix", "suffix")
        temp.deleteOnExit()

        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp))).withCloseable {
            writer ->
                new BufferedReader(new InputStreamReader(new FileInputStream(file))).withCloseable {
                    reader ->
                        int lineNo = 0
                        def line = ''
                        while ((line != reader.readLine()) != null) {
                            String[] templine = [line]
                            if (matchToAnyRule(line, file, lineNo)) {
                                rules.each { templine[0] = substitution(it, templine[0], file) }
                            }
                            writer.writeLine(templine[0])
                            lineNo++
                        }
                }
        }
        Files.copy(temp.toPath(), file.toPath(),
                StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES)
    }


    private static boolean lineMatches(Object rule, String line, File file, int lineNo) {
        if (rule in ModifyRule) {
            (rule as ModifyRule).lineMatches(line, file, lineNo)
        }

        if (rule in ModificationRule) {
            (rule as ModificationRule).lineMatches(line)
        }

        false
    }

    private static String substitution(Object rule, String line, File file) {
        if (rule in ModificationRule) {
            (rule as ModificationRule).substitution(line)
        }

        if (rule in ModifyRule) {
            (rule as ModifyRule).substitution(line, file)
        }

        line
    }

}
