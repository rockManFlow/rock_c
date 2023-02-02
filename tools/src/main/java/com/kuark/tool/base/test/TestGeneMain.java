package com.kuark.tool.base.test;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author rock
 * @detail
 * @date 2020/8/11 10:08
 */
public class TestGeneMain extends AbstractProcessor {
    public static void main(String[] args) {
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
