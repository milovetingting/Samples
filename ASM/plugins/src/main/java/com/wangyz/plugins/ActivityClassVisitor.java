package com.wangyz.plugins;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class ActivityClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    private static final String CLASS_NAME_ACTIVITY = "androidx/appcompat/app/AppCompatActivity";

    private static final String METHOD_NAME_ONCREATE = "onCreate";

    private static final String METHOD_NAME_ONDESTROY = "onDestroy";

    public ActivityClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
                      String[] interfaces) {
        mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                                     String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        if (CLASS_NAME_ACTIVITY.equals(mClassName)) {
            if (METHOD_NAME_ONCREATE.equals(name)) {
                System.out.println("-------------------- ActivityClassVisitor,visit method:" + name +
                        " --------------------");
                return new ActivityOnCreateMethodVisitor(Opcodes.ASM5, methodVisitor);
            } else if (METHOD_NAME_ONDESTROY.equals(name)) {
                System.out.println("-------------------- ActivityClassVisitor,visit method:" + name +
                        " --------------------");
                return new ActivityOnDestroyMethodVisitor(Opcodes.ASM5, methodVisitor);
            }
        }
        return methodVisitor;
    }
}