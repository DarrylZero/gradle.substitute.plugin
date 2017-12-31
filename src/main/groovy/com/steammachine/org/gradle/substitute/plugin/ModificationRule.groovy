package com.steammachine.org.gradle.substitute.plugin

import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

/**
 * {@link com.steammachine.org.gradle.substitute.plugin.ModificationRule}
 * com.steammachine.org.gradle.substitute.plugin.ModificationRule
 *
 * Each implementation must imply that if {@link #lineMatches}  returns true
 *
 *
 * ModificationRule rule = new RuleClass()
 * ...
 * ...
 * ...
 *
 * Strnig line = ...
 * if (rule.lineMatches(line)) {
 *     Strnig modifiedline = rule.substitution(line)
 *     assert !rule.lineMatches(modifiedline)
 * }
 *
 */
@Api(value = State.MAINTAINED)
interface ModificationRule {

    /**
     * Checks if the given line matches
     * @param a line
     * @return {@code true} if line matched the condition
     */
    boolean lineMatches(String line)

    /**
     *
     * @param line - not null
     * @return modified line
     */
    String substitution(String line)

}
