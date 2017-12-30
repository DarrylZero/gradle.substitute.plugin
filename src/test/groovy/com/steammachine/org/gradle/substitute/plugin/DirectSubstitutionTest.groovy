package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DirectSubstitution
import org.junit.Assert
import org.junit.Test

class DirectSubstitutionTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DirectSubstitution",
                DirectSubstitution.class.name);
    }

    @Test
    void testSmoke() {
        def substitution = new DirectSubstitution()
        substitution.setFind 'Luise'
        substitution.setSubstitute 'Armstrong'
        Assert.assertEquals(false, substitution.lineMatches('Hello dolly'))
        Assert.assertEquals(true, substitution.lineMatches('this is Luise dolly'))
        Assert.assertEquals('this is Armstrong dolly', substitution.substitution('this is Luise dolly'))
    }



}
