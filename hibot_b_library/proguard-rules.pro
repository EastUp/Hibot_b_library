###########################以下是AndroidStudio自带的混淆配置协议###############################

# 表示混淆时不使用大小写混合类名
-dontusemixedcaseclassnames

# 表示不跳过library中的非public的类
-dontskipnonpubliclibraryclasses

# 打印混淆的详细信息
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize

# 表示不进行校验,这个校验作用 在java平台上的
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.
#使用注解需要添加
-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
#指定不混淆所有的JNI方法
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
#所有View的子类及其子类的get、set方法都不进行混淆
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
# 不混淆Activity中参数类型为View的所有方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
# 不混淆Enum类型的指定方法
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 不混淆Parcelable和它的子类，还有Creator成员变量
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

# 不混淆R类里及其所有内部static类中的所有static变量字段
-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
# 不提示兼容库的错误警告
-dontwarn android.support.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

#################################以下是自己添加的混淆协议###################################
#下面代码中的路径配置,你要修改成与你相对应的路径

#引入依赖包rt.jar（jdk路径）(注意：如在makeJar的时候提示指定了两次，可以将其注释掉)
#-libraryjars 'C:\Android_Develop_Tools\Java\jdk1.8.0_101\jre\lib\rt.jar'

#引入依赖包android.jar(android SDK路径)(注意：如在makeJar的时候提示指定了两次，可以将其注释掉)
#-libraryjars 'C:\Android_Develop_Tools\sdk\platforms\android-23\android.jar'

#如果用到Appcompat包，需要引入(注意：如在makeJar的时候提示指定了两次，可以将其注释掉)
#-libraryjars 'D:\AndroidStudioProjects\SerialSend\mylibrary\build\intermediates\exploded-aar\com.android.support\appcompat-v7\23.4.0\jars\classes.jar'
#-libraryjars 'D:\AndroidStudioProjects\SerialSend\mylibrary\build\intermediates\exploded-aar\com.android.support\support-v4\23.4.0\jars\classes.jar'

#忽略警告
-ignorewarnings
#保证是独立的jar,没有任何项目引用,如果不写就会认为我们所有的代码是无用的,从而把所有的代码压缩掉,导出一个空的jar
-dontshrink
#保护泛型
-keepattributes Signature
#下面的Test类将不会被混淆，这样的类是需要被jar包使用者直接调用的
-keep class com.xg.east.hibot_b_library.service.usbSendType.ClassicType{
    public *;
}
-keep class com.xg.east.hibot_b_library.service.usbSendType.EleType{
    public *;
}
-keep class com.xg.east.hibot_b_library.service.usbSendType.LightType{
    public *;
}
-keep class com.xg.east.hibot_b_library.service.usbSendType.MotorPType{
    public *;
}
-keep interface com.xg.east.hibot_b_library.Communicate{
    *;
}
-keep interface com.xg.east.hibot_b_library.ICallBack{
    *;
}
-keep interface com.xg.east.hibot_b_library.USEntity{
    *;
}

-keep class com.xg.east.hibot_b_library.face_utils.FaceDiscernUtil{
    public *;
}

-keep class com.xg.east.hibot_b_library.face_utils.FaceDiscernUtil$DiscernResult{
    *;
}

-keep class com.xg.east.hibot_b_library.face_utils.FaceUtils{
    public *;
}

-keep class com.xg.east.hibot_b_library.action.HandAction{
     public *;
 }

 -keep class com.xg.east.hibot_b_library.action.LightControll{
      public *;
  }

 -keep class com.xg.east.hibot_b_library.action.ClassicControll{
        public *;
 }
 -keep class com.xg.east.hibot_b_library.action.HeadControll{
         public *;
 }

-keep class com.xg.east.hibot_b_library.SerialSend{
    public *;
}

-keep class com.xg.east.hibot_b_library.face_utils.DiscernResult{
    public *;
}

-keep class com.xg.east.hibot_b_library.bean.DetectWithAttribute{
    public *;
}

-keep class com.xg.east.hibot_b_library.bean.DetectWithAttribute$FacesBean{
    public *;
}

-keep class com.xg.east.hibot_b_library.bean.DetectWithAttribute$FacesBean$AttributesBean{
    public *;
}

-keep class com.faceplusplus.api.*{
   *;
}

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


#okio
-dontwarn okio.**
-keep class okio.**{*;}

#-keepclasseswithmembernames class com.example.mylibrary.UsbSend{
#
#}

#-dontwarn com.blackbox.hibot.mylibrary.**
#-keep class com.blackbox.hibot.mylibrary.**{
#    *;
#}