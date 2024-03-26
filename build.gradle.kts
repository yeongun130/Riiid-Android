// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(ProjectProperties.GradlePlugin.ANDROID_APPLICATION) version Versions.GRADLE_ANDROID apply false
    id(ProjectProperties.GradlePlugin.ANDROID_LIBRARY) version Versions.GRADLE_ANDROID apply false
    id(ProjectProperties.GradlePlugin.KOTLIN_ANDROID) version Versions.GRADLE_KOTLIN apply false
    id(ProjectProperties.GradlePlugin.KTLINT) version Versions.GRADLE_KTLINT
    id(ProjectProperties.GradlePlugin.HILT_PLUGIN) version Versions.HILT apply false
}