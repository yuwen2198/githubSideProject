package com.davidhsu.githubproject

import timber.log.Timber

object LogUtil {

    private var className: String = String()
    private var methodName: String = String()
    private var lineNumber: Int = 0

    private fun createLog(log: String): String = "[$className : line:$lineNumber , $methodName] $log"

    private fun getMethodNames(elements: Array<StackTraceElement>) {
        className = elements[1].fileName
        methodName = elements[1].methodName
        lineNumber = elements[1].lineNumber
    }

    fun e(message: String) {
        getMethodNames(Throwable().stackTrace)
        Timber.e(createLog(message))
    }

    fun i(message: String) {
        getMethodNames(Throwable().stackTrace)
        Timber.i(createLog(message))
    }

    fun d(message: String) {
        getMethodNames(Throwable().stackTrace)
        Timber.d(createLog(message))
    }

    fun v(message: String) {
        getMethodNames(Throwable().stackTrace)
        Timber.v(createLog(message))
    }

    fun w(message: String) {
        getMethodNames(Throwable().stackTrace)
        Timber.w(createLog(message))
    }

    fun build(vararg content: String): String {
        val sb = StringBuilder()
        for (i in content.indices) {
            if ((i + 1) % 2 != 0) {
                sb.append("(").append(content[i]).append(":")
            } else {
                sb.append(content[i]).append(") ")
            }
        }

        return sb.toString()
    }
}
