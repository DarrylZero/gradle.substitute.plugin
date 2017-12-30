package com.steammachine.org.gradle.substitute.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * {@link com.steammachine.org.gradle.substitute.plugin.SubstitutePlugin}
 */
class SubstitutePlugin implements Plugin<Project> {

    public static final String TASK_NAME = "modifysources"
    public static final int VERSION = 1

    void apply(Project project) {
        project.tasks.create(TASK_NAME, FileModifier) {
            group = "modification"
        }
    }

    def static version()  {
        VERSION
    }
}
