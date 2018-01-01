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
 * if (rule.lineMatches(line, file, 0)) {
 *     Strnig modifiedline = rule.substitution(line)
 *     assert !rule.lineMatches(modifiedline)
 * }
 *
 */
@Api(value = State.MAINTAINED)
interface ModifyRule {

    /**
     * Checks if the given line matches
     * @param a line
     * @param file file object - not null
     * @param lineNo a line no
     * @return {@code true} if line matched the condition
     */
    boolean lineMatches(String line, File file, int lineNo)

    /**
     *
     * @param line - not null
     * @return modified line
     */
    String substitution(String line, File file)

}
