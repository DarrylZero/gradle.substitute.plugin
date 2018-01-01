package com.steammachine.org.gradle.substitute.plugin.types

enum State {

    /**
     * feature is for internal use - can be deleted removed or renamed - do not use it in custom code
     */
    INTERNAL,

    /**
     * feature is in experimental mode - can be deleted removed or renamed - use in custom code with caution
     */
    EXPERIMENT,

    /**
     * Feature is approved to be {@link State#MAINTAINED}  but is still checked
     */
    INCUBATING,

    /**
     * Feature is checked and can be used.
     */
    MAINTAINED
}