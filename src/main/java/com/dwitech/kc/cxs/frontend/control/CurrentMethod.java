package com.dwitech.kc.cxs.frontend.control;

import java.lang.StackWalker.StackFrame;
import java.util.stream.Stream;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;
import static java.lang.StackWalker.getInstance;

public interface CurrentMethod {
    static String find(Stream<StackFrame> frames) {
        return frames
                .dropWhile(frame -> !frame.getDeclaringClass().equals(CurrentMethod.class))
                .map(StackFrame::getMethodName)
                .skip(1)
                .findFirst()
                .get();
    }
    static String name() {
        return getInstance(RETAIN_CLASS_REFERENCE).walk(CurrentMethod::find);
    }
}