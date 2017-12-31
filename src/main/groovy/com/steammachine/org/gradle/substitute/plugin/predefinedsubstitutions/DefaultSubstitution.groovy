package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import com.steammachine.org.gradle.substitute.plugin.FinalInspection
import com.steammachine.org.gradle.substitute.plugin.ModificationRule
import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

/**
 *
 * {@link com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DefaultSubstitution}
 * com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DefaultSubstitution
 */
@Api(value = State.MAINTAINED)
class DefaultSubstitution implements ModificationRule, FinalInspection {

    @Override
    boolean lineMatches(String line) {
        false
    }

    @Override
    String substitution(String line) {
        ''
    }

    @Override
    void inspect() {
    }
}

