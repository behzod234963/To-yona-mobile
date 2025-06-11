plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.mr.anonym.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":domain"))
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.room.common)
    implementation(libs.roomCoroutinesSupport)
    kapt(libs.roomCompiler)
    implementation(libs.gsonConverter)
    implementation(libs.retrofit2)
    implementation(libs.okHttp)
    implementation(platform(libs.okHttpBom))
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.paging3)
    implementation(libs.paging3JetpackCompose)

    implementation(libs.daggerHilt)
    implementation(libs.daggerHilt.navigation.compose)
    kapt(libs.daggerHiltCompiler)
    kapt(libs.daggerHiltAndroidCompiler)

    implementation(libs.tink)
}