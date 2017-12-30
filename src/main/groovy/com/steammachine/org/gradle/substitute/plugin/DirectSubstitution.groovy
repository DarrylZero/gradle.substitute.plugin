package com.steammachine.org.gradle.substitute.plugin
/**
 *
 *
 * {@link DirectSubstitution}
 * com.steammachine.org.gradle.substitute.plugin.DirectSubstitution
 */
class DirectSubstitution implements ModificationRule {

    String find
    String substitute

    DirectSubstitution() {
    }

    void setFind(String find) {
        this.find = find
    }

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
