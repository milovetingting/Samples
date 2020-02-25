package com.wangyz.plugins;

import org.objectweb.asm.MethodVisitor;

import static com.android.tools.r8.org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.gradle.internal.impldep.org.mozilla.classfile.ByteCode.POP;

/**
 * @author wangyz
 * @time 2020/2/25 9:23
 * @description ActivityOnCreateMethodVisitor
 */
public class ActivityOnCreateMethodVisitor extends MethodVisitor {

    public ActivityOnCreateMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitCode() {

        mv.visitLdcInsn("ASMPlugin");
        mv.visitLdcInsn("-------------------- MainActivity onCreate --------------------");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;" +
                "Ljava/lang/String;)I", false);
        mv.visitInsn(POP);

        super.visitCode();

    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }
}
