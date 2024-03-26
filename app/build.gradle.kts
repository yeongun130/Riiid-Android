import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.GradlePlugin.ANDROID_APPLICATION)
    id(ProjectProperties.GradlePlugin.KOTLIN_ANDROID)
    id(ProjectProperties.GradlePlugin.HILT_PLUGIN)
    kotlin(ProjectProperties.GradlePlugin.KAPT)
}

android {
    namespace = "com.example.riiidandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.riiidandroid"
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "BASE_URL",
            getApiKey("BASE_URL")
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Dependency.Androidx.CORE_KTX)
    implementation(Dependency.Androidx.APP_COMPAT)

    implementation(Dependency.Google.MATERIAL)
    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.Compose.NAVIGATION_COMPOSE)

    implementation(Dependency.Compose.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_TOOL)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)

    testImplementation(Dependency.UnitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}