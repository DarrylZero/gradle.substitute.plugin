package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

@Api(value = State.INCUBATING)
interface FinalInspection {

    /**
     * method called at the end of all inspections
     */
    void inspect()
}
