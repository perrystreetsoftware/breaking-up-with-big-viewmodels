import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)

    /** Hilt */
    // alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.perrystreet.nobigviewmodels"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.perrystreet.nobigviewmodels"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
    buildFeatures {
        compose = true
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(platform(libs.koin.bom))
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.annotations)
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation.compose)

    /** Hilt */
    // implementation(libs.hilt.android)
    // implementation(libs.hilt.navigation.compose)
    // ksp(libs.hilt.compiler)
    // testImplementation(libs.hilt.android.testing)
    // kspTest(libs.hilt.compiler)

    testImplementation(libs.koin.test)
    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest)
    testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.kotlinx.coroutines.test)

    ksp(libs.koin.ksp.compiler)

    testRuntimeOnly(libs.junit.jupiter.engine)

    testFixturesImplementation(libs.kotest)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.leakcanary.android)
}
