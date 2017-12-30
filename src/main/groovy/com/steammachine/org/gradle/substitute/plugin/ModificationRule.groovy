package com.steammachine.org.gradle.substitute.plugin

/**
 * {@link com.steammachine.org.gradle.substitute.plugin.ModificationRule}
 * com.steammachine.org.gradle.substitute.plugin.ModificationRule
 */
interface ModificationRule {

    boolean lineMatches(String line)

    String substitution(String line)

}
