plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.workmate"
    compileSdk = 37



    defaultConfig {
        applicationId = "com.example.workmate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)


    // Retrofit and Gson
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.retrofit)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // Compose Navigation с Hilt
    implementation(libs.androidx.hilt.navigation.compose)

    // Основная библиотека Room
    implementation(libs.androidx.room.runtime)
    // Корутины — для асинхронной работы с Room
    implementation(libs.androidx.room.ktx)
    // Компилятор Room (для KSP)
    ksp(libs.androidx.room.compiler)

    // Базовые корутины
    implementation(libs.kotlinx.coroutines.core)
    // Корутины для Android (чтобы удобно работать с жизненным циклом)
    implementation(libs.kotlinx.coroutines.android)

    detektPlugins(libs.detekt.formatting)

}

// Настройка Detekt
detekt {
    toolVersion = libs.versions.detekt.get()
    config = files("detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
}

// Задача для проверки кода
tasks.register("checkCode") {
    dependsOn("detekt")
    description = "Запускает Detekt и проверяет код на соответствие правилам"
}