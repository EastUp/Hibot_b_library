package com.xg.east.hibot_b_library.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class PersonBean {

    /**
     * status : OK
     * persons : [{"person_id":"71b1050035b24579a5919b3c9c336580","name":"钱超"},{"person_id":"ad4fe9fe9b8446b28ad0e82a3ebbc756","name":"施友岚"},{"person_id":"c93d09d186204aefa18e139a39072e9d","name":"西瓜"},{"person_id":"796322bb98534aa085e6edcdc5f167a2","name":"李杰"},{"person_id":"2a3e837a7edb4d9f8b7ef2ac295b4d5a","name":"袁明武"},{"person_id":"c57787be46de48e4afb6a2575144168f","name":"刘天成"},{"person_id":"e06bd07c948944bf8b9b506bf30d9b66","name":"李龙龙"},{"person_id":"efab225c15d34542b72aa5b1407ca1df","name":"邓强"},{"person_id":"f47a8553bc8b48f68763fd912af77b75","name":"李子妖"},{"person_id":"c2ec11f44dd0421f8c52911f117cbf6c","name":"老于"},{"person_id":"87c0e1d0661d41af85abf209bbce7dad","name":"赵老师"},{"person_id":"ca66a50f13d044a391adc5cceb84551f","name":"孟汗"},{"person_id":"e8121bb9441042debe6d19ba2967be01","name":"阿潮好潮"},{"person_id":"5a5b813af4584ceca49aa577cffe5c56","name":"张哲"},{"person_id":"4aad4c72feb9452c8803e5ee32e1394c","name":"于子谦宝宝"},{"person_id":"ab9c4cdeca6f46af8eccaf5c1d087090","name":"皮迪"}]
     */

    private String status;
    /**
     * person_id : 71b1050035b24579a5919b3c9c336580
     * name : 钱超
     */

    private List<PersonsEntity> persons;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PersonsEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonsEntity> persons) {
        this.persons = persons;
    }

    public static class PersonsEntity {
        private String person_id;
        private String name;

        public String getPerson_id() {
            return person_id;
        }

        public void setPerson_id(String person_id) {
            this.person_id = person_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
