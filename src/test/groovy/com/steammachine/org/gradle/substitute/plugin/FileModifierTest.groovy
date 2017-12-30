package com.steammachine.org.gradle.substitute.plugin

import org.junit.Assert
import org.junit.Test

class FileModifierTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.FileModifier",
                FileModifier.class.name);
    }

}
