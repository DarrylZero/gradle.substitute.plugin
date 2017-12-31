package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import org.junit.Assert
import org.junit.Test

class FunctionSubstitutionTest {

    @Test
    void testNameIntegrity() {
        Assert.assertEquals("com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.FunctionSubstitution",
                FunctionSubstitution.class.name);
    }

    @Test
    void testCreation() {
        FunctionSubstitution.newInstance()
    }

    @Test
    void testCalc() {
        def instance = FunctionSubstitution.newInstance()
        instance.find = 'light'
        instance.operation = { 'LIGHT' }
        Assert.assertTrue(instance.lineMatches(' I would sit alone and watch your light'))
        Assert.assertEquals(' I would sit alone and watch your LIGHT',
                instance.substitution(' I would sit alone and watch your light'))
    }



}
