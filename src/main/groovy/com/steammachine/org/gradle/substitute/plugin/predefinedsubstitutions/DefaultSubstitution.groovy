package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import com.steammachine.org.gradle.substitute.plugin.FinalInspection
import com.steammachine.org.gradle.substitute.plugin.ModificationRule
import com.steammachine.org.gradle.substitute.plugin.ModifyRule
import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

/**
 *
 * {@link com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DefaultSubstitution}
 * com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DefaultSubstitution
 */
@Api(value = State.MAINTAINED)
class DefaultSubstitution implements ModifyRule, FinalInspection {

    @Override
    boolean lineMatches(String line, File file, int lineNo) {
        return false
    }

    @Override
    String substitution(String line, File file) {
        ''
    }

    @Override
    void inspect() {
    }
}

