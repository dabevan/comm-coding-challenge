package io.kotlintest.provided

import io.kotlintest.AbstractProjectConfig
import io.kotlintest.Spec
import io.kotlintest.extensions.ConstructorExtension
import io.kotlintest.extensions.ProjectLevelExtension
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier


@Suppress("unused")
class ProjectConfig : AbstractProjectConfig() {


    override fun beforeAll() {
        println("BEFORE")
    }

    override fun afterAll() {
        println("AFTER")
    }


    override fun extensions(): List<ProjectLevelExtension> = listOf(DependencyInjectionExtension(listOf(
            DependencyFactory { "HELLO" }
    )))


}

private interface DependencyFactory<T : Any> : () -> T {
    fun canCreate(type: KClassifier?): Boolean

    companion object {
        inline operator fun <reified T : Any> invoke(crossinline function: () -> T): DependencyFactory<T> =
                object : DependencyFactory<T> {
                    override fun invoke(): T = function()
                    override fun canCreate(type: KClassifier?): Boolean = type == T::class
                }
    }
}

private class DependencyInjectionExtension(private val factories: List<DependencyFactory<*>>) : ConstructorExtension {

    override fun <T : Spec> instantiate(clazz: KClass<T>): Spec? {
        if (clazz.constructors.size != 1) {
            throw IllegalArgumentException("Class ${clazz.qualifiedName} has more than one constructor")
        }

        val arguments = clazz.constructors.first().parameters.map { parameter ->
            factories.firstOrNull { it.canCreate(parameter.type.classifier) }?.let { it() }
                    ?: throw IllegalArgumentException("Unknown argument type ${parameter.type.classifier} in " +
                            "class ${clazz.qualifiedName}")
        }

        return clazz.constructors.first().call(*arguments.toTypedArray())
    }
}


