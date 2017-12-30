package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DirectSubstitution
import org.junit.Assert
import org.junit.Test

class ModificationRuleTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.ModificationRule",
                ModificationRule.class.name);
    }
}
