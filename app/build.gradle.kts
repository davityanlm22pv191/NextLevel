plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	id("org.jetbrains.kotlin.plugin.serialization")
	id("com.google.devtools.ksp")
	id("org.jetbrains.kotlin.plugin.parcelize")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.example.tutorplace"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.example.tutorplace"
		minSdk = 28
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {

		debug {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}

		release {
			isMinifyEnabled = true
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
		freeCompilerArgs = listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}

	defaultConfig {
		buildConfigField(
			"String",
			"SERVER_URL",
			"\"https://mybackend-aoz8.onrender.com/\""
		)
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.ui.text)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)

	// Compose
	implementation(libs.ui)
	implementation(libs.material3)
	implementation(libs.ui.tooling.preview)
	debugImplementation(libs.ui.tooling)

	// Splash Screen
	implementation(libs.androidx.core.splashscreen)

	// Navigation 3
	implementation(libs.androidx.navigation3.ui)
	implementation(libs.androidx.navigation3.runtime)

	// Lifecycle & MVI helpers
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.lifecycle.viewmodel.compose)

	// Coroutines
	implementation(libs.kotlinx.coroutines.android)

	// Hilt (Dagger)
	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)
	implementation(libs.androidx.hilt.navigation.compose)

	// Jetpack Data Store
	implementation(libs.androidx.datastore.preferences)

	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.converter.gson)

	// Chucker
	debugImplementation(libs.chucker)
	releaseImplementation(libs.chucker.no.op)

	// Coil
	implementation(libs.coil)

	// Serializer
	implementation(libs.kotlinx.serialization.json)
}