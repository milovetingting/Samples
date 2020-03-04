package com.wangyz.annotation_compiler;

import com.google.auto.service.AutoService;
import com.wangyz.annotation.BindView;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

/**
 * @author wangyz
 * @time 2020/3/3 9:04
 * @description AnnotationsCompiler
 */
@AutoService(Processor.class)
public class AnnotationsCompiler extends AbstractProcessor {

    private static final String PACKAGE_NAME_BINDER = "com.wangyz.binder";

    Filer filer;

    /**
     * 支持的Java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 支持的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        //类：TypeElement
        //方法：ExecutableElement
        //属性：VariableElement

        Map<String, List<VariableElement>> map = new HashMap<>();

        for (Element element : elements) {
            VariableElement variableElement = (VariableElement) element;
            String activityName = variableElement.getEnclosingElement().getSimpleName().toString();
            List<VariableElement> variableElements = map.get(activityName);
            if (variableElements == null) {
                variableElements = new ArrayList<>();
                map.put(activityName, variableElements);
            }
            variableElements.add(variableElement);
        }

        if (map.size() > 0) {
            Writer writer = null;
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String activityName = iterator.next();
                List<VariableElement> variableElements = map.get(activityName);

                //获取包名
                TypeElement typeElement =
                        (TypeElement) variableElements.get(0).getEnclosingElement();
                String packageName =
                        processingEnv.getElementUtils().getPackageOf(typeElement).toString();

                try {
                    JavaFileObject sourceFile =
                            filer.createSourceFile(packageName + "." + activityName +
                                    "_ViewBinding");
                    writer = sourceFile.openWriter();
                    writer.write("package " + packageName + ";\n");
                    writer.write("import " + PACKAGE_NAME_BINDER + ".IBinder;\n");
                    writer.write("public class "+activityName+"_ViewBinding implements IBinder<"+packageName+"."+activityName+">{\n");
                    writer.write("@Override\n");
                    writer.write("public void bind("+packageName+"."+activityName+" target){\n");
                    for(VariableElement variableElement:variableElements)
                    {
                        //获取名字
                        String variableName = variableElement.getSimpleName().toString();
                        //获取ID
                        int id = variableElement.getAnnotation(BindView.class).value();
                        //得到类型
                        TypeMirror typeMirror = variableElement.asType();
                        writer.write("target."+variableName+"=("+typeMirror+")target.findViewById("+id+");\n");
                    }
                    writer.write("\n}\n}");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(writer!=null)
                    {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return false;
    }
}
