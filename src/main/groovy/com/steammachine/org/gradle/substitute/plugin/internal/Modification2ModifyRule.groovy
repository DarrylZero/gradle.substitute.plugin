package com.steammachine.org.gradle.substitute.plugin.internal

import com.steammachine.org.gradle.substitute.plugin.ModificationRule
import com.steammachine.org.gradle.substitute.plugin.ModifyRule
import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

@Api(State.INTERNAL)
class Modification2ModifyRule implements ModifyRule {

    final ModificationRule rule

    static ModifyRule adapt(ModificationRule rule) {
        new Modification2ModifyRule(rule)
    }

    Modification2ModifyRule(ModificationRule rule) {
        this.rule = Objects.requireNonNull(rule)
    }

    @Override
    boolean lineMatches(String line, File file, int lineNo) {
        return rule.lineMatches(line)
    }

    @Override
    String substitution(String line, File file) {
        return rule.substitution(line)
    }

}
