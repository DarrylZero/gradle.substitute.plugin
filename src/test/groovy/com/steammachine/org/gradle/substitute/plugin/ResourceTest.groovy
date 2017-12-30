package com.steammachine.org.gradle.substitute.plugin

import org.junit.Assert
import org.junit.Test

class ResourceTest {

    @Test
    void testFindNecessaryResources() {
        ResourceTestss.class.classLoader.
                getResourceAsStream('META-INF/gradle-plugins/com.steammachine.org.gradle.substitute.plugin.properties').
                withCloseable {
                    stream ->
                        def properties = new Properties()
                        properties.load(stream)
                        Assert.assertEquals('com.steammachine.org.gradle.substitute.plugin.SubstitutePlugin',
                                properties.getProperty('implementation-class'))

                }
    }
}
