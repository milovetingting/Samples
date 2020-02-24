package com.wangyz.local

import org.gradle.api.Plugin
import org.gradle.api.Project

class LocalPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.create("customLocalConfig", CustomLocalConfig)

        project.task('CustomLocalTask', group: 'CustomTask').doLast {
            println "CustomLocalTask,key=${project.customLocalConfig.key}"
        }
    }
}