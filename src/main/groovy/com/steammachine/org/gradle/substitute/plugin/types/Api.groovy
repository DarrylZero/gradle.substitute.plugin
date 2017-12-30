package com.steammachine.org.gradle.substitute.plugin.types

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.*

/**
 *
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target([TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, ANNOTATION_TYPE])
@interface Api {

    /**
     *
     * @return api level
     */
    State value() default State.EXPERIMENT
}
