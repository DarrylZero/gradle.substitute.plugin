package com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions

import com.steammachine.org.gradle.substitute.plugin.types.Api
import com.steammachine.org.gradle.substitute.plugin.types.State

import java.util.function.Function

@Api(value = State.INCUBATING)
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
    boolean lineMatches(String line, File file, int lineNo) {
        find != null && operation != null && line.contains(find)
    }

    @Override
    String substitution(String line, File file) {
        line.replaceAll(find, operation.apply(line))
    }
}
