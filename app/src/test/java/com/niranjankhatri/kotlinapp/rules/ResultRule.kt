package com.niranjankhatri.kotlinapp.rules

import com.niranjankhatri.kotlinapp.testing.junit.FactorialClass
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.math.BigInteger

class ResultRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement(){
            @Throws(Throwable::class)
            override fun evaluate() {
                FactorialClass.result = BigInteger.ONE
                try {
                    base.evaluate()
                } finally {
                    FactorialClass.result = BigInteger.ONE
                }
            }

        }
    }
}