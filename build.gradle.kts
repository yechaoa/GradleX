// Top-level build file where you can add configuration options common to all sub-projects/modules.

/**
 * 版本管理示例
 * 使用：implementation("androidx.core:core-ktx:${rootProject.extra.get("core-ktx")}")
 */
extra.apply {
    set("applicationId", "com.yechaoa.gradlex")
    set("minSdk", 23)
    set("targetSdk", 33)
    set("core-ktx", "1.9.0")
    set("appcompat", "1.6.1")
}

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

//@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
//    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.9.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("com.dorongold.task-tree") version "2.1.1"
}