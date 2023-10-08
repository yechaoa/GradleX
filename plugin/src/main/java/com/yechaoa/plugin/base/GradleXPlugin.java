package com.yechaoa.plugin.base;


import com.android.build.gradle.AppExtension;
import com.yechaoa.plugin.extension.CommonPluginExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ModuleVersionIdentifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2023/8/8.
 * Describe :
 */

class GradleXPlugin implements Plugin<Project> {

    private final String TAG = "GradleXPlugin >>>>> ";

    @Override
    public void apply(Project project) {
        CommonPluginExtension extension = project.getExtensions().create(CommonPluginExtension.NAME_SPACE, CommonPluginExtension.class);

        /*
         * 扩展的配置要在 project.afterEvaluate 之后获取哦
         * 因为配置阶段完成，才能读取参数
         * 且配置完成，才能拿到所有的依赖
         */
        project.afterEvaluate(pro -> {
            if (extension.printDependencies) {
                System.out.println(TAG + "已开启依赖打印");
                doPrintDependencies(project);
            } else {
                System.out.println(TAG + "已关闭依赖打印");
            }

            if (extension.analysisSo) {
                System.out.println(TAG + "已开启so打印");
                doAnalysisSo(project);
            } else {
                System.out.println(TAG + "已关闭so打印");
            }

        });

    }

    private void doAnalysisSo(Project project) {
        AppExtension androidExtension = project.getExtensions().getByType(AppExtension.class);
        androidExtension.getApplicationVariants().all(applicationVariant -> {
            System.out.println(TAG + "applicationVariant.getName() = " + applicationVariant.getName());

            Configuration configuration = project.getConfigurations().getByName(applicationVariant.getName() + "CompileClasspath");
            configuration.forEach(file -> {
                String fineName = file.getName();
                System.out.println(TAG + "fine name = " + fineName);
                if (fineName.endsWith(".jar") || fineName.endsWith(".aar")) {
                    try {
                        JarFile jarFile = new JarFile(file);
                        for (Enumeration enums = jarFile.entries(); enums.hasMoreElements(); ) {
                            JarEntry jarEntry = (JarEntry) enums.nextElement();
                            if (jarEntry.getName().endsWith(".so")) {
                                System.out.println(TAG + "----- so name = " + jarEntry.getName());
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
    }

    private void doPrintDependencies(Project project) {
        AppExtension androidExtension = project.getExtensions().getByType(AppExtension.class);

        androidExtension.getApplicationVariants().all(applicationVariant -> {
            // debug/release也可以加配置
            System.out.println(TAG + "applicationVariant.getName() = " + applicationVariant.getName());
            Configuration configuration = project.getConfigurations().getByName(applicationVariant.getName() + "CompileClasspath");

            List<String> androidLibs = new ArrayList<>();
            List<String> otherLibs = new ArrayList<>();

            // 所有的依赖，包括依赖中的依赖
            configuration.getResolvedConfiguration().getLenientConfiguration().getAllModuleDependencies().forEach(resolvedDependency -> {
                ModuleVersionIdentifier identifier = resolvedDependency.getModule().getId();
                if (identifier.getGroup().contains("androidx") || identifier.getGroup().contains("com.google") || identifier.getGroup().contains("org.jetbrains")) {
                    androidLibs.add(identifier.getGroup() + ":" + identifier.getName() + ":" + identifier.getVersion());
                } else {
                    otherLibs.add(identifier.getGroup() + ":" + identifier.getName() + ":" + identifier.getVersion());
                }
            });

            System.out.println("--------------官方库 start--------------");
            androidLibs.forEach(System.out::println);
            System.out.println("--------------官方库 end--------------");

            System.out.println("--------------三方库 start--------------");
            otherLibs.forEach(System.out::println);
            System.out.println("--------------三方库 end--------------");
        });
    }
}
