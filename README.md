# com.steammachine.org:gradle.substitute.plugin


**About**

Plugin adds one task named **modifySources** to the  group **modification** of type com.steammachine.org.gradle.substitute.plugin.FileModifier to the project.
Tasks of that type can be called independently. A task scans project source sourceSets and replaces found `tokens` 
with other tokens according to applied substitution rules.



**Application**

To apply plugin do the following

```groovy
buildscript {
    repositories {
        maven { url 'https://clojars.org/repo' }
    }

    dependencies {
        classpath 'com.steammachine.org:gradle.substitute.plugin:1.0.0'
    }
}

apply plugin: 'com.steammachine.org.gradle.substitute.plugin'

```

**Execution**

To execute a task call

```text
gradle modifysources
```

**Modification rules**

modification rule is groovy class that implements interface

```groovy 
com.steammachine.org.gradle.substitute.plugin.ModificationRule 
```
and has public no-args constructor

A modification rule is applied using `rule` method of task  

in example given below defined and applied DateTimeSubstitution rule
the rule finds #DATE `token` in source code base and replaces it with current time in format dd.MM.yyyy
```groovy 
class DateTimeSubstitution implements ModificationRule {
    String find = '#DATE'
    String timeFormat = "dd.MM.yyyy"

    DateTimeSubstitution() {
    }

    void setFind(String lineToFind) {
        this.find = lineToFind
    }

    void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat
    }

    boolean lineMatches(String line) {
        find != null && line.contains(find)
    }

    String substitution(String line) {
        line.replaceAll(find, new SimpleDateFormat(timeFormat).format(new Date()))
    }
}

modifysources.rule(DateTimeSubstitution)
```


There is a set of predefined modification rules defined in a package distributed with plugin 


**Predefined modification rules**


Predefined modification rules are situated in package 
```groovy 
com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions
```




