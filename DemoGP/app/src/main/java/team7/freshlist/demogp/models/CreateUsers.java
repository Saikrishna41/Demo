package team7.freshlist.demogp.models;

public class CreateUsers {


        private String first_name;
        private String last_name;

        public CreateUsers(String first_name, String last_name) {
                this.first_name = first_name;
                this.last_name = last_name;
        }

        public String getFirst_name() {
                return first_name;
        }

        public String getLast_name() {
                return last_name;
        }


}