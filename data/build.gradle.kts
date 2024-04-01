import java.io.FileInputStream
import java.util.Properties

plugins {
    id(ProjectProperties.GradlePlugin.ANDROID_LIBRARY)
    id(ProjectProperties.GradlePlugin.KOTLIN_ANDROID)
    kotlin(ProjectProperties.GradlePlugin.KAPT)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

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
}

dependencies {

    implementation(project(":domain"))

    implementation(Dependency.Androidx.CORE_KTX)
    implementation(Dependency.Androidx.APP_COMPAT)
    implementation(Dependency.Androidx.DATASTORE)

    implementation(Dependency.Google.MATERIAL)
    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)
    implementation(Dependency.Google.GSON)

    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.Paging.PAGING)

    implementation(Dependency.Room.ROOM)

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