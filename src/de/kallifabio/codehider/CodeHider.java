package de.kallifabio.codehider;

import java.io.File;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import de.lpk.util.JarUtils;

public class CodeHider implements Opcodes {

    public static void hideCode(String input, String output) throws Throwable {
        File file = new File(input);
        Map<String, ClassNode> classes = JarUtils.loadClasses(file);
        Map<String, byte[]> out = JarUtils.loadNonClassEntries(file);
        for (ClassNode cn : classes.values()) {
            for (MethodNode mn : cn.methods) {
                for (FieldNode fn : cn.fields) {
                    if (mn != null && (mn.access / ACC_SYNTHETIC) == 0) {
                        mn.access += ACC_SYNTHETIC;
                    }
                    if (fn != null && (fn.access / ACC_SYNTHETIC) == 0) {
                        fn.access += ACC_SYNTHETIC;
                    }
                }
            }
            ClassWriter cw = new ClassWriter(0);
            cn.accept(cw);
            out.put(cn.name, cw.toByteArray());
            System.out.println("Code von den Klasen: " + cn.name + " wurde versteckt!");
        }
        JarUtils.saveAsJar(out, output);
        System.out.println("Done with obfuscation!");
    }

}
