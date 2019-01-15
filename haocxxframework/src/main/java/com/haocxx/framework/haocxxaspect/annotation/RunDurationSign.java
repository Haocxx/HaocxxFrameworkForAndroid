package com.haocxx.framework.haocxxaspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Haocxx
 * on 2018/11/28
 *
 * https://blog.csdn.net/xwh_1230/article/details/78213160
 */

@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface RunDurationSign {
}

/*
  It finally works. I wrongly put the follow gradle codes in library instead of
  app package. So it shall be put in build.gradle of the package which need use
  the annotation.

  Update in 2019/1/15
  refer to : https://www.jianshu.com/p/f90e04bcb326
 */
//final def log = project.logger
//final def variants = project.android.applicationVariants
//
//variants.all { variant ->
//    if (!variant.buildType.isDebuggable()) {
//        log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
//        return
//    }
//
//    JavaCompile javaCompile = variant.javaCompile
//    javaCompile.doLast {
//        String[] args = ["-showWeaveInfo",
//                         "-1.5",
//                         "-inpath", javaCompile.destinationDir.toString(),
//                         "-aspectpath", javaCompile.classpath.asPath,
//                         "-d", javaCompile.destinationDir.toString(),
//                         "-classpath", javaCompile.classpath.asPath,
//                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
//        log.debug "ajc args: " + Arrays.toString(args)
//
//        MessageHandler handler = new MessageHandler(true)
//        new Main().run(args, handler)
//        for (IMessage message : handler.getMessages(null, true)) {
//            switch (message.getKind()) {
//                case IMessage.ABORT:
//                case IMessage.ERROR:
//                case IMessage.FAIL:
//                    log.error message.message, message.thrown
//                    break
//                case IMessage.WARNING:
//                    log.warn message.message, message.thrown
//                    break
//                case IMessage.INFO:
//                    log.info message.message, message.thrown
//                    break
//                case IMessage.DEBUG:
//                    log.debug message.message, message.thrown
//                    break
//            }
//        }
//    }
//}