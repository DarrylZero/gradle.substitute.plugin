package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import org.junit.Assert
import org.junit.Test

class DefaultSubstitutionTest {


    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DefaultSubstitution",
                DefaultSubstitution.class.name);
    }

    @Test
    void testCreation() {
        DefaultSubstitution.newInstance()
    }
}
