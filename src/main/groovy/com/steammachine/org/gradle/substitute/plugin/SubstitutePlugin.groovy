package com.steammachine.org.gradle.substitute.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class SubstitutePlugin implements Plugin<Project> {

//    public static final String EXTENSION_NAME = "copyresources"
    public static final int VERSION = 1

    void apply(Project project) {
            project.tasks.create("modifysources", FileModifier) {
            group = "modification"
        }
    }

    def static version()  {
        VERSION
    }
}
