package com.niranjankhatri.kotlinapp.rules

import com.niranjankhatri.kotlinapp.testing.junit.MyClass
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MyResultRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement(){
            @Throws(Throwable::class)
            override fun evaluate() {
                MyClass.result = 1
                try {
                    base.evaluate()
                } finally {
                    MyClass.result = 1
                }
            }

        }
    }
}