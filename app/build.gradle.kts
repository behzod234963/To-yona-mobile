plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kspPlugin)
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mr.anonym.toyonamobile"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mr.anonym.toyonamobile"
        minSdk = 26
        //noinspection OldTargetApi
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
    buildFeatures {
        compose = true
    }
    ksp {
        arg("option_name", "option_value")
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}
kotlin{
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation(libs.daggerHilt)
    implementation(libs.daggerHilt.navigation.compose)
    implementation(libs.androidx.hilt.work)
    ksp(libs.daggerHiltCompiler)
//    kapt(libs.daggerHiltCompiler)
//    kapt(libs.daggerHiltAndroidCompiler)
//    kapt(libs.daggerHiltWorkCompiler)

    implementation(libs.navigation)
    implementation(libs.kotlinCoroutines)
    implementation(libs.viewModel)
    implementation(libs.viewModelForCompose)
    implementation(libs.lifecycleCompose)
    implementation(libs.savedState)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.dataStore)
    implementation(libs.biometricAuth)

    implementation(libs.androidx.room.common)
    implementation(libs.roomSqlite)
    implementation(libs.roomCoroutinesSupport)
    ksp(libs.roomCompiler)
//    kapt(libs.roomCompiler)

    implementation(libs.cardIOscanner)

    implementation(libs.pullToRefresh)

    implementation(libs.lottieanimations)

    implementation(libs.systemUiController)

    implementation(libs.gsonConverter)
    implementation(libs.retrofit2)

    implementation(libs.okHttp)
    implementation(platform(libs.okHttpBom))
    implementation(libs.okHttpLoggingInterceptor)

    implementation(libs.paging3)
    implementation(libs.paging3JetpackCompose)

    implementation(libs.tink)

    implementation(libs.workManager)
    implementation(libs.androidx.hilt.common)
    implementation(libs.firebase.functions.ktx)

    implementation(platform(libs.firebase.bom))

    debugImplementation(libs.chuckerInterceptorDebug){
        exclude(group = "com.android.support", module =  "support-compat")
    }
    releaseImplementation(libs.chuckerInterceptorRelease)

    implementation(libs.firebaseMessaging)
}
