package com.wangyz.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project> {
    @Override
    void apply(Project project){
         project.extensions.create("customConfig", CustomConfig)

        project.task('CustomTask', group: 'CustomTask').doLast {
            println "CustomTask,key=${project.customConfig.key}"
        }
    }
}