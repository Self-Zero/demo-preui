package com.oac.project.common.utils;

/**
 * 常量类（枚举）
 */
public class Constants {
    /**
     * 员工工种
     *
     */
    public enum WorkerType {
        SMALL_WORKER(0,"小工"),
        WORKER_CARPENTRY(1, "木工"),
        WORKER_PAINTER(3, "面漆工"),
        WORKER_PRIMER_ID(2, "底漆工");

        private final Integer value;
        private final String name;

        WorkerType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

}
