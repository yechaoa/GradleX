package com.yechaoa.plugin;

import org.gradle.api.provider.Property;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2023/8/11.
 * Describe :
 */
interface DependenciesPluginExtension {
    Property<Boolean> getEnable();
}
