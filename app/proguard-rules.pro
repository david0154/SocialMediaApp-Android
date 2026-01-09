# ProGuard rules for SocialMediaApp Android
# Optimize and obfuscate code for release builds

# Keep line numbers for crash reporting
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep all application code (to be customized as needed)
-keep class com.davidstudioz.socialmedia.** { *; }

# Keep BuildConfig
-keep class com.davidstudioz.socialmedia.BuildConfig { *; }

# Keep all model classes
-keep class com.davidstudioz.socialmedia.data.models.** { *; }
-keepclassmembers class com.davidstudioz.socialmedia.data.models.** { *; }

# Keep database entities
-keep class com.davidstudioz.socialmedia.data.local.entities.** { *; }

# ==================== Retrofit ====================
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep Gson annotations
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# ==================== Room ====================
-keep class androidx.room.** { *; }
-keep @androidx.room.* class * { *; }
-keepclassmembers @androidx.room.Entity class * { *; }
-keepclassmembers @androidx.room.DatabaseView class * { *; }

# ==================== Hilt ====================
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.Hilt*
-keepclasseswithmembers class * {
    @dagger.hilt.* <fields>;
    @dagger.hilt.* <methods>;
}

# ==================== OkHttp ====================
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# ==================== Timber ====================
-keep class timber.log.** { *; }

# ==================== Glide ====================
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
  <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# ==================== OneSignal ====================
-keep class com.onesignal.** { *; }
-keepclassmembers class com.onesignal.** { *; }

# ==================== Media3/ExoPlayer ====================
-keep class androidx.media3.** { *; }
-keep class com.google.android.exoplayer2.** { *; }

# ==================== AndroidX ====================
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# ==================== Google Play Services ====================
-keep class com.google.android.gms.** { *; }
-keep interface com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# ==================== Firebase ====================
-keep class com.google.firebase.** { *; }
-keep interface com.google.firebase.** { *; }

# ==================== Kotlin ====================
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }
-dontwarn kotlin.**

# ==================== Kotlin Coroutines ====================
-keep class kotlinx.coroutines.** { *; }
-keep interface kotlinx.coroutines.** { *; }

# ==================== Common ====================
-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.**
-dontnote com.google.common.**
-dontwarn java.beans.Transient

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep constructors of classes that are used by reflection
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep parcelables
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
