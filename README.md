# com.steammachine.org:gradle.substitute.plugin


**About**

Plugin adds one task named **modifysources** to the group **modification** of type com.steammachine.org.gradle.substitute.plugin.FileModifier to the project.
Tasks of that type can be called independently, without plugin. A task scans project source sourceSets and replaces found `tokens` 
with other `tokens` according to applied substitution rules.



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

A modification rule is applied using `rule` method of task.  

```groovy 
    modifysources.rule(ARuleType) {
        /* here goes a configuration block */
    }
```

In example given below defined and applied DateTimeSubstitution rule searches #DATE `token` in project sources and replaces it with current time in format `dd.MM.yyyy`. 

```groovy 
class DateTimeSubstitution implements ModifyRule {
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

    boolean lineMatches(String line, File file, int lineno) {
        find != null && line.contains(find)
    }

    String substitution(String line, File file) {
        line.replaceAll(find, new SimpleDateFormat(timeFormat).format(new Date()))
    }
}

modifysources.rule(DateTimeSubstitution) {
   /* here goes the configuration block for the rule */
   timeFormat = 'dd.MM.yyyy hh:mm:ss'
}
```


There is a set of predefined modification rules defined in a package distributed with plugin 


**Predefined modification rules**


Predefined modification rules are situated in package 
```groovy 
com.steammachine.org.gradle.substitute.plugin.predefinedsubstitutions
```

**Task properties**

The task FileModifier (com.steammachine.org.gradle.substitute.plugin.FileModifier) does not have any other settings.






