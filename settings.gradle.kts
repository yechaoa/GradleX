import java.util.Properties
import java.io.File

println("---Gradle：开始初始化了")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        // 本地maven仓库地址
        maven("./plugin/build/maven-repo")
    }
    // 插件解析策略 useVersion/useModule
    resolutionStrategy {
        eachPlugin {
//            println("--- >>>  requested === " + requested.toString())
            if (requested.id.id == "com.android.application") {
                //useVersion("2.0")
                //println("---android plugin added---")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        // 本地maven仓库地址
        maven("./plugin/build/maven-repo")

        // 阿里云仓库
        maven("https://maven.aliyun.com/nexus/content/repositories/releases")
        maven("https://maven.aliyun.com/repository/public/")

        flatDir {
            dirs("libs")
        }
    }
}
rootProject.name = "GradleX"
include(":app")
include(":plugin")

// 加载local.properties文件
val localProperties = Properties()
val localPropertiesFile = file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}
// 从localProperties中获取useLocal属性
val useLocal = localProperties.getProperty("useLocal", "false").toBoolean()

println("local.properties useLocal = $useLocal")

if (useLocal) {
    include(":yutilskt")
    project(":yutilskt").projectDir = File("../YUtils/yutilskt")
}

// 插件式应用 切换逻辑
apply(from = "UseLocalPlugin.gradle")

//gradle.settingsEvaluated {
//    println("---Gradle：settingsEvaluated Settings对象评估完毕")
//}
//
//gradle.projectsLoaded {
//    println("---Gradle：projectsLoaded 准备加载Project对象了")
//}
//
//gradle.allprojects {
//    beforeEvaluate {
//        println("---Gradle：Project beforeEvaluate Project开始评估，对象是 = " + project.name)
//    }
//    afterEvaluate {
//        println("---Gradle：Project afterEvaluate Project评估完毕，对象是 = " + project.name)
//    }
//}
//
//gradle.projectsEvaluated {
//    println("---Gradle：projectsEvaluated 所有Project对象评估完毕")
//}


//gradle.addListener(new TaskExecutionListener(){
//
//    @Override
//    void beforeExecute(Task task) {
//        println("---Gradle：Task beforeExecute---")
//
//    }
//
//    @Override
//    void afterExecute(Task task, TaskState state) {
//        println("---Gradle：Task afterExecute---")
//    }
//})


//gradle.addListener(new TaskActionListener() {
//
//    @Override
//    void beforeActions(Task task) {
//        println("---Gradle：Task beforeActions---")
//    }
//
//    @Override
//    void afterActions(Task task) {
//        println("---Gradle：Task afterActions---")
//    }
//})


/**
 * buildFinished和addListener在8.0版本之后unsupported
 * See https://docs.gradle.org/8.0/userguide/configuration_cache.html#config_cache:requirements:build_listeners
 */
//gradle.buildFinished {
//    println("---Gradle：buildFinished 构建结束了")
//}
//
//gradle.addListener(new BuildListener() {
//    @Override
//    void settingsEvaluated(Settings settings) {
//
//    }
//
//    @Override
//    void projectsLoaded(Gradle gradle) {
//
//    }
//
//    @Override
//    void projectsEvaluated(Gradle gradle) {
//
//    }
//
//    @Override
//    void buildFinished(BuildResult result) {
//
//    }
//})
