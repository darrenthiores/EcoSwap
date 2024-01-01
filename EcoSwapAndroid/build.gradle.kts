plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Deps.kotlinVersion
}

android {
    namespace = "com.darrenthiores.ecoswap.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.darrenthiores.ecoswap.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.darrenthiores.ecoswap.TestHiltRunner"

        buildConfigField("String", "PREF_NAME", "\"ECO_SWAP_PREFERENCE\"")
        buildConfigField("String", "ENCRYPTED_PREF_NAME", "\"TOKEN_PREFRENCE\"")
        buildConfigField("String", "USER_TOKEN_KEY", "\"USER_TOKEN\"")
        buildConfigField("String", "USER_REFRESH_TOKEN_KEY", "\"USER_REFRESH_TOKEN\"")
        buildConfigField("String", "SHOW_ON_BOARDING_KEY", "\"SHOW_ON_BOARDING\"")
        buildConfigField("String", "SHOW_AUTH_KEY", "\"SHOW_AUTH\"")

    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Deps.composeUi)
    implementation(Deps.composeUiTooling)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterial)
    implementation(Deps.activityCompose)
    implementation(Deps.activity)
    implementation(Deps.composeIconsExtended)
    implementation(Deps.composeNavigation)
    implementation(Deps.coilCompose)

    implementation(Deps.viewModel)
    implementation(Deps.viewModelCompose)
    implementation(Deps.hiltViewModel)

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)
    kapt(Deps.hiltCompiler)

    implementation(Deps.ktorAndroid)

    implementation(Deps.encPreferences)

    implementation(Deps.timber)

    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.jUnit)
    androidTestImplementation(Deps.composeTesting)
    androidTestImplementation(Deps.rules)
    debugImplementation(Deps.composeTestManifest)

    kaptAndroidTest(Deps.hiltAndroidCompiler)
    androidTestImplementation(Deps.hiltTesting)
}