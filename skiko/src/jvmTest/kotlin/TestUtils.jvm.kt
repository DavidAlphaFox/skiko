package org.jetbrains.skiko.tests

import kotlinx.coroutines.runBlocking
import org.jetbrains.skia.Data
import org.jetbrains.skia.impl.BufferUtil
import org.jetbrains.skia.impl.InteropScope
import org.jetbrains.skia.impl.NativePointer
import org.jetbrains.skia.makeFromFileName
import java.nio.ByteBuffer

actual fun <T> runTest(block: suspend () -> Unit): T {
    error("It's a fake actual. Not expected to be called")
}

fun runTest(block: suspend () -> Unit): Unit {
    return runBlocking { block() }
}

internal actual fun InteropScope.allocateBytesForPixels(size: Int): NativePointer {
    return BufferUtil.getPointerFromByteBuffer(ByteBuffer.allocateDirect(size))
}

actual annotation class SkipJsTarget

actual annotation class SkipWasmTarget

actual typealias SkipJvmTarget = org.junit.Ignore

actual annotation class SkipNativeTarget

actual fun makeFromFileName(path: String?): Data = Data.Companion.makeFromFileName(path)

actual val isDebugModeOnJvm: Boolean
    get() {
        return System.getProperty("skiko.test.debug") == "true"
    }