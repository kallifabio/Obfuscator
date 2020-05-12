package de.lpk.mapping.remap.impl;

import de.lpk.mapping.MappedClass;
import de.lpk.mapping.MappedMember;
import de.lpk.mapping.remap.MappingMode;

public class ModeNone extends MappingMode {

    @Override
    public String getClassName(MappedClass cn) {
        return cn.getOriginalName();
    }

    @Override
    public String getMethodName(MappedMember mn) {
        return mn.getOriginalName();
    }

    @Override
    public String getFieldName(MappedMember fn) {
        return fn.getOriginalName();
    }
}
