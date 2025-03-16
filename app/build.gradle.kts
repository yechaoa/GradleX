import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
//    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.yechaoa.plugin.gradleX")
}

/**
 * 想看printDependencies效果：
 * 1.先执行./gradlew publish发布本地
 * 2.再解开这里和上面插件的注释 //id("com.yechaoa.plugin.gradleX")
 * 3.还有project > build.gradle里面的classpath
 */
gradleX {
    printDependencies = false
    analysisSo = false
    checkSnapshot = false
    blockSnapshot = false
//    permissionsToRemove = ["android.permission.RECORD_AUDIO","android.permission.WRITE_EXTERNAL_STORAGE"]
}

/**
 * task 示例抽出去 解开注释可看效果
 */
//apply(from = "../task.gradle")

/**
 * plugin 示例抽出去 解开注释可看效果
 */
//apply(from = "../plugin.gradle")

/**
 *  多渠道配置示例 解开注释可看效果
 */
//apply(from = "../channel.gradle")

/**
 * 从动态参数获取VersionName和VersionCode
 */
fun getVersionNameByProperty(): String {
    val name: String = if (project.hasProperty("VersionName") && project.property("VersionName") != null) {
        project.properties["VersionName"] as String
    } else {
        "2.0-default"
    }
    return name
}

fun getVersionCodeByProperty(): Int {
    val code: Int = if (project.hasProperty("VersionCode") && project.property("VersionCode") != null) {
        project.properties["VersionCode"].toString().toInt()
    } else {
        2
    }
    return code
}

val keystoreProperties = Properties()
val keystorePropertiesFile: File = rootProject.file("jks/keystore.properties")
if (keystorePropertiesFile.exists()){
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}


android {
    namespace = "com.yechaoa.gradlex"
    compileSdk = 35

    defaultConfig {
        applicationId = properties["applicationId"].toString()
        minSdk = properties["minSdk"].toString().toInt()
        targetSdk = properties["targetSdk"].toString().toInt()
        versionCode = getVersionCodeByProperty()
        versionName = getVersionNameByProperty()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            // splits中配置了abi，这里就不需要配置了
            //noinspection ChromeOsAbiSupport / x86 / x86_64 / arm64-v8a / armeabi-v7a
//            abiFilters.addAll(mutableSetOf("arm64-v8a", "armeabi-v7a"))
        }

//        manifestPlaceholders["appName"] = "GradleX"
//        manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"

    }

    applicationVariants.configureEach {
        val buildTypeName = this.buildType.name
        this.outputs.all {
            val output = this as com.android.build.gradle.api.BaseVariantOutput
            // 动态修改VersionName和VersionCode
//            if (buildTypeName == "debug") {
//                val apkOutput = this as? com.android.build.gradle.api.ApkVariantOutput
//                apkOutput?.let {
//                    it.versionCodeOverride = 3
//                    it.versionNameOverride = "3.0"
//                }
//            }
            // 动态删除清单文件里的权限 可用plugin中的gradleX { permissionsToRemove = ["android.permission.RECORD_AUDIO","android.permission.WRITE_EXTERNAL_STORAGE"] }
//            output.processManifest.doLast {
//                // 获取manifest文件
//                val manifestOutFile = output.processResourcesProvider.get().getManifestFile()
//                // 读取manifest文件
//                var manifestContent = manifestOutFile.readText()
//                // 删除指定权限
//                manifestContent = manifestContent.replace("android.permission.RECORD_AUDIO", "android.permission.INTERNET")
//                // 再写回manifest文件
//                manifestOutFile.writeText(manifestContent)
//            }
        }
    }

    signingConfigs {
//        create("release") {
//            if (keystorePropertiesFile.exists()){
//                keyAlias = keystoreProperties["keyAlias"] as String
//                keyPassword = keystoreProperties["keyPassword"] as String
//                storeFile = file(keystoreProperties["storeFile"] as String)
//                storePassword = keystoreProperties["storePassword"] as String
//            }
//        }
    }

    buildTypes {
        getByName("debug") {
//            versionNameSuffix = "-测试包"
            isDebuggable = true
        }
        getByName("release") {
//            versionNameSuffix = "-正式包"
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs.getByName("release")
        }
    }

    // ./gradlew assembleRelease -PisRelease=true
    val isRelease = project.hasProperty("isRelease") && project.property("isRelease") == "true"
    println("splits isRelease === : $isRelease")
    // ./gradlew assembleRelease -PisRelease=true -PonlyArm64=true
    val onlyArm64 = project.hasProperty("onlyArm64") && project.property("onlyArm64") == "true"
    println("splits onlyArm64 === : $onlyArm64")

    // ABI 分包
    splits {
        abi {
            isEnable = isRelease
            reset()
            if (onlyArm64) {
                include("arm64-v8a")
                isUniversalApk = false
            } else {
                include("arm64-v8a", "armeabi-v7a")
                isUniversalApk = true
            }
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

// 加载local.properties文件
val localProperties = Properties()
val localPropertiesFile = file("../local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}
// 从localProperties中获取useLocal属性
val useLocal = localProperties.getProperty("useLocal", "false").toBoolean()

dependencies {

    if (project.hasProperty("isTest")) {
        println("---hasProperty isTest yes")
//        if (Boolean.valueOf(getProperty("isTest"))) {
//            println("---isTest true")
//        } else {
//            println("---isTest false")
//        }
    } else {
        println("---hasProperty isTest no")
    }

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

//    implementation(project(":plugin"))

    implementation("com.squareup.okhttp3:okhttp:4.9.3")
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // 华为推送 华为渠道test
//    "huaweiImplementation"("com.huawei.hms:push:6.11.0.300")
//    or
//    add("huaweiImplementation", "com.huawei.hms:push:6.11.0.300")

    //音视频终端 SDK ---打印so测试依赖
    //8.全功能：直播推流（含超低延时直播、RTC连麦）＋短视频＋播放器＋美颜特效 https://help.aliyun.com/zh/apsara-video-sdk/product-overview/integrated-sdk-upgrade-instructions
    implementation("com.aliyun.aio:AliVCSDK_Premium:6.4.0")

    // 看Gradle源码用
//    implementation("com.android.tools.build:gradle:8.0")

    // 依赖替换使用
    implementation("com.github.yechaoa.YUtils:yutilskt:3.4.0")
    // 在local.properties中配置useLocal=true，或执行：./gradlew assembleDebug -PuseLocal=true
//    if (useLocal) {
//        implementation(project(":yutilskt"))
//    } else {
//        implementation("com.github.yechaoa.YUtils:yutilskt:3.4.0")
//    }
}


/**
 * 强制依赖版本
 * 示例：com.squareup.okhttp3:okhttp:4.10.0
 */
configurations.configureEach {
    // 开启版本冲突编译直接报错模式...
//    resolutionStrategy{
//        failOnVersionConflict()
//    }

    // 版本冲突解析策略
    resolutionStrategy.eachDependency {
        val details = this as DependencyResolveDetails
        val requested = details.requested
        if (requested.group == "com.squareup.okhttp3" && requested.name == "okhttp") {
            details.useVersion("4.10.0")
        }
        // 还可以使用startsWith通配，比如room-compiler、room-rxjava2、room-testing
//        if (requested.group == "androidx.room" && requested.name.startsWith("room")) {
//            details.useVersion "2.5.0"
//        }
    }

    // 依赖替换
    resolutionStrategy.dependencySubstitution {
        // 在local.properties中配置useLocal=true，或执行：./gradlew assembleDebug -PuseLocal=true
//        if (useLocal) {
//            // 把yutilskt:3.4.0替换成源码依赖
//            substitute(module("com.github.yechaoa.YUtils:yutilskt:3.4.0")).using(project(":yutilskt"))
//        }
        // 把yutilskt替换成远端依赖
//        substitute(project (":yutilskt")).using(module("com.github.yechaoa.YUtils:yutilskt:3.4.0"))
        // 把yutilskt:3.3.3替换成yutilskt:3.4.0
//        substitute(module("com.github.yechaoa.YUtils:yutilskt:3.3.3")).using(module("com.github.yechaoa.YUtils:yutilskt:3.4.0"))
    }
}