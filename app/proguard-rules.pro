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


-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keepclassmembers enum * { *; }

#picasso open
    -dontwarn com.squareup.okhttp.**
#picasso closed

#greenrobot eventbus open
    -keepattributes *Annotation*
    -keepclassmembers class ** {
        @org.greenrobot.eventbus.Subscribe <methods>;
    }
    -keep enum org.greenrobot.eventbus.ThreadMode { *; }
    # Only required if you use AsyncExecutor
    -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
        <init>(java.lang.Throwable);
    }
#greenrobot eventbus closed

#retrofit open
    # Platform calls Class.forName on types which do not exist on Android to determine platform.
    -dontnote retrofit2.Platform
    # Platform used when running on Java 8 VMs. Will not be used at runtime.
    -dontwarn retrofit2.Platform$Java8
    # Retain generic type information for use by reflection by converters and adapters.
    -keepattributes Signature
    # Retain declared checked_blue exceptions for use by a Proxy instance.
    -keepattributes Exceptions
#retrofit closed

#retrofit rxjava square open
    -dontwarn retrofit2.adapter.rxjava.CompletableHelper$**
#retrofit rxjava square close

#Common open
    -keepattributes InnerClasses

    -dontwarn

    -ignorewarnings

    -keepattributes Exceptions

    -keep class sun.misc.Unsafe { *; }

    #pojo path open
        -keep class com.demoproj.db.entity.** { *; }
#        -keep class com.eveproject.utility.bus.** { *; }
        -keep class com.demoproj.model.** { *; }
        -keep class com.demoproj.utils.** { *; }
#        -keep class com.sergiopaniegoblanco.webrtcexampleapp.editphotos.** { *; }
#        -keep class com.sergiopaniegoblanco.webrtcexampleapp.RemoteParticipant { *; }
    #pojo path closed
#Common closed

# Retrofit, OkHttp, Gson
-keepattributes *Annotation*
-keepattributes Signature
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# OkHttp3
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Rxjava-promises

-keep class com.darylteo.rx.** { *; }

-dontwarn com.darylteo.rx.**

# RxJava 0.21

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontnote rx.internal.util.PlatformDependent