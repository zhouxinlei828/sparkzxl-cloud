package com.sparksys.activiti.infrastructure.entity.model;

import lombok.Data;

import java.util.List;

@Data
public class ProcessAttribute {
    private String resourceId;
    private Properties properties;
    private Stencil stencil;
    private Bounds bounds;
    private Stencilset stencilset;
    private List<ChildShapes> childShapes;
    private List<?> ssextensions;

    @Data
    public static class Properties {
        private String process_id;
        private String name;
        private String documentation;
        private String process_author;
        private String process_version;
        private String process_namespace;
        private String executionlisteners;
        private String eventlisteners;
        private String signaldefinitions;
        private String messagedefinitions;
    }

    @Data
    public static class Stencil {

        private String id;

    }

    @Data
    public static class Bounds {

        private LowerRight lowerRight;
        private UpperLeft upperLeft;

        @Data
        public static class LowerRight {

            private int x;

            private int y;

        }

        @Data
        public static class UpperLeft {

            private int x;

            private int y;
        }
    }

    @Data
    public static class Stencilset {

        private String url;

        private String namespace;

    }

    @Data
    public static class ChildShapes {

        private String resourceId;
        private PropertiesBeanX properties;
        private Stencil stencil;
        private Bounds bounds;
        private TargetBean target;
        private List<?> childShapes;
        private List<OutgoingBean> outgoing;
        private List<?> dockers;

        @Data
        public static class PropertiesBeanX {
            private String overrideid;
            private String name;
            private String documentation;
            private String executionlisteners;
            private String initiator;
            private String formkeydefinition;
            private String formproperties;
        }

        @Data
        public static class TargetBean {

            private String resourceId;

        }

        @Data
        public static class OutgoingBean {

            private String resourceId;


        }
    }
}
