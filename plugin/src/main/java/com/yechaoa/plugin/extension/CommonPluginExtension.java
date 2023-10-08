package com.yechaoa.plugin.extension;


/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2023/10/7.
 * Describe : Plugin Extension Params
 */
public class CommonPluginExtension {

    public static String NAME_SPACE = "gradleX";

    /**
     * 是否打印输出所有依赖并区分二方三方，默认关闭
     */
    public boolean printDependencies = false;

    /**
     * 是否打印输出so和依赖的关系，默认关闭
     */
    public boolean analysisSo = false;

}
