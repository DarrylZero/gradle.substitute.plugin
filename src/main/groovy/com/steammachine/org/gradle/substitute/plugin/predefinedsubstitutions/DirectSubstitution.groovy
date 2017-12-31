package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import com.steammachine.org.gradle.substitute.plugin.ModificationRule
import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

/**
 *
 *
 * {@link DirectSubstitution}
 * com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DirectSubstitution
 * com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions.DirectSubstitution
 */
@Api(value = State.MAINTAINED)
class DirectSubstitution extends DefaultSubstitution {

    String find
    String substitute

    /**
     *
     * @param sets a token to find
     */
    void setFind(String find) {
        this.find = find
    }

    /**
     *
     * @param sets a token that found token will be substituted with
     */
    void setSubstitute(String substitute) {
        this.substitute = substitute
    }

    @Override
    boolean lineMatches(String line) {
        substitute != null && find != null && line.contains(find)
    }

    @Override
    String substitution(String line) {
        line.replaceAll(find, substitute)
    }
}
