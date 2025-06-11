# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.mr.anonym.toyonamobile.di.app.** { *; }
-keep class * extends android.app.Activity
-keep class * extends androidx.lifecycle.ViewModel

# Compose
-keep class androidx.compose.** {*;}
-keep class androidx.ui.** {*;}
-dontwarn androidx.compose.**

# Dagger/Hilt
-keep class dagger.**{*;}
-keep class javax.inject.**{*;}
-dontwarn dagger.**

#Remote
-keep class retrofit2.**{*;}
-dontwarn retrofit2.**
#

-keep class com.google.gson.**{*;}
-keepattributes Signature
-keepattributes *Annotation*

# Local
-keep class androidx.room.**{*;}
-keep class * extends androidx.room.RoomDatabase
-keepclassmembers class *{
    @androidx.room.* *;
}

-assumenosideeffects class android.os.Debug{
    public static void waitForDebugger();
    public static boolean isDebuggerConnected();
}
-assumenosideeffects class android.util.Log{
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}