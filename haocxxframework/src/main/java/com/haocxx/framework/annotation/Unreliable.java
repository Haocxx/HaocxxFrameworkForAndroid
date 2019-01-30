package com.haocxx.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to sign unreliable codes which might not work in some specific situations.
 *
 * Created by Haocxx
 * on 2019/1/30
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Unreliable {
}
