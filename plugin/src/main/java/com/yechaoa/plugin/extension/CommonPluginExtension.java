package com.yechaoa.plugin.extension;


import java.util.ArrayList;
import java.util.List;

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

    /**
     * snapshot版本检查，默认关闭
     */
    public boolean checkSnapshot = false;

    /**
     * snapshot版本检查 如有，打断编译，默认关闭
     */
    public boolean blockSnapshot = false;

    /**
     * 需要移除的权限
     */
    public List<String> permissionsToRemove = new ArrayList<>();
}
