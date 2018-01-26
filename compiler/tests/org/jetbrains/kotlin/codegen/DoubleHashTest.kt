package org.jetbrains.kotlin.codegen

import org.jetbrains.kotlin.test.ConfigurationKind
import org.jetbrains.org.objectweb.asm.ClassReader
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode
import org.jetbrains.org.objectweb.asm.tree.ClassNode
import org.jetbrains.org.objectweb.asm.util.Textifier
import org.jetbrains.org.objectweb.asm.util.TraceMethodVisitor
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

class DoubleHashTest : CodegenTestCase() {
    fun testDoubleHash() {
        doTest("class Op_Kt { fun test() { ##\"HW\"+1+2+3 } }", "_Kt", Collections.emptyList())
    }

    private fun doTest(sourceText: String, classSuffix: String, expectedOrder: List<String>) {
        createEnvironmentWithMockJdkAndIdeaAnnotations(ConfigurationKind.JDK_ONLY)
        myFiles = CodegenTestFiles.create("file.kt", sourceText, myEnvironment!!.project)

        val classFileForObject = generateClassesInFile().asList().first { it.relativePath.endsWith("$classSuffix.class") }
        val reader = ClassReader(classFileForObject.asByteArray())
        val classNode = ClassNode()
        reader.accept(classNode,0)
        val methods = classNode.methods
        for (m in methods) {
            val inList = m.instructions;
            println(m.name)
            for (inst in inList) {
                println(insnToString(inst))
            }
        }
        println(classFileForObject.asText())
    }

    fun insnToString(insn: AbstractInsnNode) : String {
        insn.accept(mp)
        val sw = StringWriter()
        printer.print(PrintWriter(sw))
        printer.getText().clear()
        return sw.toString()
    }

    val printer = Textifier()
    val mp = TraceMethodVisitor(printer)
}