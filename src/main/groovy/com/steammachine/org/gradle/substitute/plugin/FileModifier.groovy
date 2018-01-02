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

    final List<Object> rules = []
    final Set<Mode> modes = []

    @TaskAction
    void perform() {


        if (Mode.DEBUG in modes) {
            println 'source sets that are used'
            println ' '
            println ' '

            project.sourceSets.each {
                sourceset -> sourceset.allSource.each { println it.absolutePath }
            }

            println ' '
            println ' '
            println "rules that are used $rules"
        }

        project.sourceSets.each {
            sourceset ->
                sourceset.allSource.each {
                    File source ->
                        if (Mode.DEBUG in modes) {
                            println "inspecting file $source.absolutePath"
                        }
                        def modified = isModified(source)
                        if (Mode.DEBUG in modes) {
                            println "file $source.absolutePath modified = $modified"
                        }

                        if (modified) {
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
    def <T> Object rule(Class<T> clazz) {
        if (Mode.DEBUG in modes) {
            println "rule is called clazz $clazz "
        }
        def instance = clazz.newInstance()
        if (Mode.DEBUG in modes) {
            println "rule instance of class $clazz is created $instance "
        }
        rules.add(instance)
        if (Mode.DEBUG in modes) {
            println "rule instance $instance of class $clazz is added to rules list"
        }
        instance
    }

    /**
     * Adds a rule class and performs its configuration
     *
     * @param clazz class to create instance [not null]
     */
    def <T> void rule(Class<T> clazz, Closure<T> config) {
        if (Mode.DEBUG in modes) {
            println "rule method is called: clazz $clazz "
        }
        def newRule = rule(clazz)
        ConfigureUtil.configureUsing(config).execute(newRule)
        if (Mode.DEBUG in modes) {
            println "rule instance $newRule is configured"
        }
    }

    /**
     * clears all set modes
     * @return
     */
    FileModifier clearMode() {
        this.modes.clear()
        this
    }

    /**
     * adds modes
     * @param modes
     */
    void setMode(Mode ... modes) {
        this.modes.addAll(modes)
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
        rules.stream().anyMatch { Object rule -> lineMatches(rule, line, file, lineNo) }
    }

    void modifyFile(File file) {
        if (Mode.DEBUG in modes) {
            println "modifying file $file.absolutePath"
        }

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


    static boolean lineMatches(Object rule, String line, File file, int lineNo) {
        if (rule instanceof ModifyRule) {
            line = (rule as ModifyRule).lineMatches(line, file, lineNo)
        }

        if (rule instanceof ModificationRule) {
            return (rule as ModificationRule).lineMatches(line)
        }

        false
    }

    static String substitution(Object rule, String line, File file) {
        if (rule instanceof ModificationRule) {
            rule = (rule as ModificationRule).substitution(line)
        }

        if (rule instanceof ModifyRule) {
            return (rule as ModifyRule).substitution(line, file)
        }

        line
    }

}
