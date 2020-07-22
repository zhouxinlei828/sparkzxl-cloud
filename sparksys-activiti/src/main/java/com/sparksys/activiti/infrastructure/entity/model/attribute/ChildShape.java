package com.sparksys.activiti.infrastructure.entity.model.attribute;

import com.sparksys.activiti.infrastructure.entity.model.ProcessAttribute;

import java.util.List;

public class ChildShape {

    private String resourceId;
    private ProcessAttribute.ChildShapes.PropertiesBeanX properties;
    private ProcessAttribute.Stencil stencil;
    private ProcessAttribute.Bounds bounds;
    private ProcessAttribute.ChildShapes.TargetBean target;
    private List<?> childShapes;
    private List<ProcessAttribute.ChildShapes.OutgoingBean> outgoing;
    private List<?> dockers;

}
