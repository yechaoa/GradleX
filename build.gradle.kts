// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        /**
         * 本地仓库
         * 想看printDependencies效果：
         * 1.先执行./gradlew publish发布本地
         * 2.再解开app > build.gradle里面的插件的注释 //id 'com.yechaoa.plugin.gradleX'
         * 3.还有app > build.gradle里面的gradleX{}
         */
        // GradleX本地仓库地址
//        classpath("com.yechaoa.plugin:gradleX:1.6-SNAPSHOT")
        // GradleX远端仓库地址
        classpath("com.github.yechaoa.GradleX:plugin:1.8")
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.dorongold.task-tree") version "2.1.1"
}