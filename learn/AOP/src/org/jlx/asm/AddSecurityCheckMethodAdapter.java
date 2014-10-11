/**
 * 
 */
package org.jlx.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author jianglx
 * 
 */
class AddSecurityCheckMethodAdapter extends MethodAdapter {
	public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
		super(mv);
	}

	public void visitCode() {
		visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker",
				"checkSecurity", "()V");
	}
}
