package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * {@link com.steammachine.org.gradle.substitute.plugin.SubstitutePlugin}
 */
@Api(value = State.MAINTAINED)
class SubstitutePlugin implements Plugin<Project> {

    public static final String TASK_NAME = 'modifysources'
    public static final String MODIFICATION = 'modification'
    public static final String BUILD = 'build'

    void apply(Project project) {
        project.tasks.create(TASK_NAME, FileModifier) {
            group = MODIFICATION

            if (project.tasks.findByName(BUILD) != null) {
                it.dependsOn(BUILD)
            }

        }
    }

}
