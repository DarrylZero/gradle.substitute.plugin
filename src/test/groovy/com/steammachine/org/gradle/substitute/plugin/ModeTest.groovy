package com.steammachine.org.gradle.substitute.plugin

import org.junit.Assert
import org.junit.Test

class ModeTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.Mode", Mode.class.name)
    }
}
