package com.wangyz.plugins;

import org.objectweb.asm.MethodVisitor;

import static com.android.tools.r8.org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.gradle.internal.impldep.org.mozilla.classfile.ByteCode.POP;

/**
 * @author wangyz
 * @time 2020/2/25 9:32
 * @description ActivityOnDestroyMethodVisitor
 */
public class ActivityOnDestroyMethodVisitor extends MethodVisitor {

    public ActivityOnDestroyMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();

        mv.visitLdcInsn("ASMPlugin");
        mv.visitLdcInsn("-------------------- MainActivity onDestroy --------------------");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;" +
                "Ljava/lang/String;)I", false);
        mv.visitInsn(POP);
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }
}
