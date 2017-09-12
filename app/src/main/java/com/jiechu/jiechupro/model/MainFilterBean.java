package com.jiechu.jiechupro.model;

import java.util.List;

/**
 * Created by allen on 2017/9/11.
 */

public class MainFilterBean {

    private List<LinesBean> lines;

    public List<LinesBean> getLines() {
        return lines;
    }

    public void setLines(List<LinesBean> lines) {
        this.lines = lines;
    }

    public static class LinesBean {
        /**
         * lname : 陇海线
         * plants : [{"pname":"洛阳供电车间","workstation":[{"id":"","wname":"义马网工区"},{"id":"","wname":"新安县网工区"},{"id":"","wname":"洛阳东网工区"},{"id":"","wname":"洛阳网工区"}]},{"pname":"邓州供电车间","workstation":[]},{"pname":"三门峡供电车间","workstation":[]},{"pname":"南阳西供电车间","workstation":[]}]
         */

        private String lname;
        private List<PlantsBean> plants;

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public List<PlantsBean> getPlants() {
            return plants;
        }

        public void setPlants(List<PlantsBean> plants) {
            this.plants = plants;
        }

        public static class PlantsBean {
            /**
             * pname : 洛阳供电车间
             * workstation : [{"id":"","wname":"义马网工区"},{"id":"","wname":"新安县网工区"},{"id":"","wname":"洛阳东网工区"},{"id":"","wname":"洛阳网工区"}]
             */

            private String pname;
            private List<WorkstationBean> workstation;

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }

            public List<WorkstationBean> getWorkstation() {
                return workstation;
            }

            public void setWorkstation(List<WorkstationBean> workstation) {
                this.workstation = workstation;
            }

            public static class WorkstationBean {
                /**
                 * id :
                 * wname : 义马网工区
                 */

                private String id;
                private String wname;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getWname() {
                    return wname;
                }

                public void setWname(String wname) {
                    this.wname = wname;
                }
            }
        }
    }
}
