package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import java.util.function.Function

class FunctionSubstitution extends DefaultSubstitution {

    String find
    Function<String, String> operation

    void setFind(String find) {
        this.find = find
    }

    void setOperation(Function<String, String> function) {
        this.operation = function
    }

    @Override
    boolean lineMatches(String line) {
        find != null && operation != null && line.contains(find)
    }

    @Override
    String substitution(String line) {
        return operation.apply(line)
    }

}
