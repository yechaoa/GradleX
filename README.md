# GradleX

[![](https://jitpack.io/v/yechaoa/GradleX.svg)](https://jitpack.io/#yechaoa/GradleX)
![](https://img.shields.io/badge/%E6%8E%98%E9%87%91-yechaoa-blue)
![](https://img.shields.io/badge/CSDN-yechaoa-green.svg)
![Profile views](https://gpvc.arturio.dev/yechaoaGradleX)

<img src="/pic/learning gradle.png"/>

### 环境（Environment）
- Android Studio Giraffe | 2022.3.1 Patch 1
- Android Gradle Plugin 8.1.1
- JDK 17

### 系列博客（Blog）

- [掘金专栏一览](https://juejin.cn/column/7123935861976072199)
- [【Gradle-1】入门Gradle，前置必读](https://juejin.cn/post/7155109977579847710)
- [【Gradle-2】一文搞懂Gradle配置](https://juejin.cn/post/7160337743552675847)
- [【Gradle-3】Gradle中的DSL，Groovy & Kotlin](https://juejin.cn/post/7166638852503765006)
- [【Gradle-4】Gradle的生命周期](https://juejin.cn/post/7170684769083555877)
- [【Gradle-5】Gradle常用命令与参数](https://juejin.cn/post/7171493698243395597)
- [【Gradle-6】一文搞懂Gradle的依赖管理和版本决议](https://juejin.cn/post/7215579793261117501)
- [【Gradle-7】Gradle构建核心之Task指南](https://juejin.cn/spost/7248207744087277605)
- [【Gradle-8】Gradle插件开发指南](https://juejin.cn/spost/7267091810380136508)
- [【Gradle-9】Gradle插件发布指南](https://juejin.cn/spost/7280062870669246525)
- [【Gradle-10】不可忽视的构建分析](https://juejin.cn/post/7282150745164005432)

### 插件使用
step 1
```agsl
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
step 2
```agsl
dependencies {
    classpath('com.github.yechaoa.GradleX:dependencies:1.0')
}
```
step 3
```agsl
plugins {
    id 'com.yechaoa.plugin.dependencies'
}

printDependencies {
    enable = true
}
```