package com.jetbrains.space.util

import java.util.*

class PropertiesReader {

    companion object {
        const val TEST_PROPERTIES_FILE = "test.properties"
    }

    fun getProperty(key: String): String {
        val properties = Properties()
        properties.load(this.javaClass.classLoader.getResourceAsStream(TEST_PROPERTIES_FILE))
        return properties.getProperty(key)
    }
}