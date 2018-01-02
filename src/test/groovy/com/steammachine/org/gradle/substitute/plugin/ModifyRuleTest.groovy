package com.steammachine.org.gradle.substitute.plugin

import org.junit.Assert
import org.junit.Test

class ModifyRuleTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.ModifyRule",
                ModifyRule.class.name);
    }

}
