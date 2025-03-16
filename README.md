# GradleX

[![](https://jitpack.io/v/yechaoa/GradleX.svg)](https://jitpack.io/#yechaoa/GradleX)
[![](https://img.shields.io/badge/%E6%8E%98%E9%87%91-yechaoa-blue)](https://juejin.cn/user/659362706101735/posts)
![](https://img.shields.io/badge/CSDN-yechaoa-green.svg)
![](https://img.shields.io/badge/Gradle-8.11.1-orange.svg)
![](https://img.shields.io/badge/AndroidStudio-Koala-yellow.svg)
![](https://img.shields.io/badge/JDK-17-red.svg)

<img src="/pic/learning gradle.png" alt="pic" />

### 编译环境（Environments）

|     Android Studio     | Gradle | Android Gradle Plugin | JDK | Kotlin |
|:----------------------:|:------:|:---------------------:|:---:|:------:|
| Android Studio Meerkat | 8.11.1 |         8.9.0         | 17  | 2.1.0  |

### 系列博客（Blog）

- [掘金专栏一览](https://juejin.cn/column/7123935861976072199)
- [【Gradle-1】入门Gradle，前置必读](https://juejin.cn/post/7155109977579847710)
- [【Gradle-2】一文搞懂Gradle配置](https://juejin.cn/post/7160337743552675847)
- [【Gradle-3】Gradle中的DSL，Groovy & Kotlin](https://juejin.cn/post/7166638852503765006)
- [【Gradle-4】Gradle的生命周期](https://juejin.cn/post/7170684769083555877)
- [【Gradle-5】Gradle常用命令与参数](https://juejin.cn/post/7171493698243395597)
- [【Gradle-6】一文搞懂Gradle的依赖管理和版本决议](https://juejin.cn/post/7215579793261117501)
- [【Gradle-7】Gradle构建核心之Task指南](https://juejin.cn/post/7248207744087277605)
- [【Gradle-8】Gradle插件开发指南](https://juejin.cn/post/7267091810380136508)
- [【Gradle-9】Gradle插件发布指南](https://juejin.cn/post/7280062870669246525)
- [【Gradle-10】不可忽视的构建分析](https://juejin.cn/post/7282150745164005432)
- [【Gradle-11】动态修改VersionName和VersionCode](https://juejin.cn/post/7282691800858705957)
- [【Gradle-12】分析so文件和依赖的关系](https://juejin.cn/post/7287429638019448888)
- [【Gradle-13】SNAPSHOT版本检查](https://juejin.cn/post/7292416512333840438)
- [【Gradle-14】编译优化之Gradle最佳配置实践](https://juejin.cn/post/7344625554529730600)
- [【Gradle-15】源码和AAR的依赖替换指南](https://juejin.cn/post/7354940230301696009)
- [【Gradle-16】直接Run和使用命令行编译有什么区别](https://juejin.cn/post/7366154691031875618)
- [【Gradle-17】动态删除清单文件中的某个权限](https://juejin.cn/post/7367701663169429554)
- [【Gradle-18】从Groovy迁移至Kotlin](https://juejin.cn/post/7372591578756841487)

### 插件功能（Feature）

- [x] 打印依赖树
- [x] SNAPSHOT版本检查
- [x] 打印so文件和依赖的关系
- [x] 动态删除清单文件中的某个权限

### 插件使用（Usage）

step 1. Add the JitPack repository to your build file

```groovy
repositories {
    // ...
    maven { url 'https://jitpack.io' }
}
```

step 2. Add the dependency

```groovy
dependencies {
    classpath('com.github.yechaoa.GradleX:plugin:1.8')
}
```

step 3. Add the Plugin Id to your build file and configure the gradleX{ } dsl

```groovy
plugins {
    id 'com.yechaoa.plugin.gradleX'
}

gradleX {
    printDependencies = false
    analysisSo = true
    checkSnapshot = true
    blockSnapshot = false
    permissionsToRemove = ['android.permission.XXX', 'android.permission.XXX']
}
```