package io.github.iamazy.elasticsearch.dsl.ibatis.compiler;

import cn.hutool.core.util.StrUtil;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringCompiler {

    private static final String PACKAGE_NAME = "io.github.iamazy.elasticsearch.dsl.ibatis.mapper";
    private static final String CLASS_TEMPLATE =
            "package {packageName};\n" +
            "import java.util.Map;" +
            "public interface {className} {\n" +
                    "{methods}" +
            "\n}";
    private static final String METHOD_TEMPLATE = "{} {}(Map<String, Object> map);";

    public static void main(String[] args) {

        String methodsStrCode = StrUtil.format(METHOD_TEMPLATE, Object.class.getName(), "testHello");
        String classShortName = "HelloMapper";

        Map<String, String> map = new HashMap<>();
        map.put("packageName", PACKAGE_NAME);
        map.put("className", classShortName);
        map.put("methods", methodsStrCode);
        String classFileCode = StrUtil.format(CLASS_TEMPLATE, map);
        System.out.println(classFileCode);

        Class<?> compile = compile(PACKAGE_NAME + "." + classShortName, classFileCode);
        System.out.println(1);
    }

    /**
     * 装载字符串成为java可执行文件
     *
     * @param className className
     * @param javaCodes javaCodes
     * @return Class
     */
    private static Class<?> compile(String className, String javaCodes) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StringJavaFileObject srcObject = new StringJavaFileObject(className, javaCodes);
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
        String flag = "-d";
        String outDir = "";
        try {
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            outDir = classPath.getAbsolutePath() + File.separator;
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
        boolean result = task.call();
        if (result) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
