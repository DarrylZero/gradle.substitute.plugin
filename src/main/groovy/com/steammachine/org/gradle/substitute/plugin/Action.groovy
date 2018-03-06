package com.steammachine.org.gradle.substitute.plugin

import static java.util.Objects.requireNonNull

enum Action {

    CHECK('check'),

    MODIFY('modify')

    final String ident;

    Action(String ident) {
        this.ident = requireNonNull ident
    }

    static Action find(String name) {
        values().stream().filter { it.ident == name}.findFirst().orElse(null)
    }


}