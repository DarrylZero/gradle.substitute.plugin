package com.steammachine.org.gradle.substitute.plugin

interface ModificationRule {

    boolean lineMatches(String line)

    String substitution(String line)

}
