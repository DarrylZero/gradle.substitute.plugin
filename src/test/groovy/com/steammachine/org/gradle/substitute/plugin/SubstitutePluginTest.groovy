package com.steammachine.org.gradle.substitute.plugin

import org.junit.Assert
import org.junit.Test

class SubstitutePluginTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.SubstitutePlugin",
                SubstitutePlugin.class.name);
    }

}
