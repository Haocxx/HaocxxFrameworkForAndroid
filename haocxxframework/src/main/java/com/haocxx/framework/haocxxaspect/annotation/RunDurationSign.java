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
