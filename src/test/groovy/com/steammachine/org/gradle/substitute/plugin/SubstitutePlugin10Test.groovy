package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State
import org.junit.Ignore
import org.junit.Test
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;


@Api(State.INTERNAL)
class SubstitutePlugin10Test {



    @Test
    @Ignore
    void testFailure() {
        def projectDir = new File(SubstitutePlugin10Test.class.getResource('resource/script1').toURI().path)
        def build = GradleRunner.create().withProjectDir(projectDir).build()
        build.tasks

    }

}
