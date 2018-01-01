package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

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
    void testCreation() {
        DirectSubstitution.newInstance()
    }

    @Test
    void testSmoke() {
        def file = File.createTempFile('prefix', 'suffix')
        file.deleteOnExit()

        def substitution = new DirectSubstitution()
        substitution.setFind 'Luise'
        substitution.setSubstitute 'Armstrong'
        Assert.assertEquals(false, substitution.lineMatches('Hello dolly', file, 0))
        Assert.assertEquals(true, substitution.lineMatches('this is Luise dolly', file, 0))
        Assert.assertEquals('this is Armstrong dolly', substitution.substitution('this is Luise dolly', file))
    }



}
