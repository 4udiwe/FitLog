plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.test{
    useJUnitPlatform()
}


dependencies {


    testImplementation(kotlin("test"))
    testImplementation(libs.mockito.core.v5130)
    testImplementation(libs.kotlin.mockito.kotlin)

    implementation(libs.kotlinx.coroutines.android)
}