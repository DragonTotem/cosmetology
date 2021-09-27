package com.zbt.cosmetology.annotation;

import android.os.Build;

import androidx.annotation.RequiresApi;

@TestTargetType
public class AnnotationTest {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TestTargetMethod("测试")
    public void handleAnnotation(){
        TestTargetType type = TestTargetType.class.getAnnotation(TestTargetType.class);
    }
}
