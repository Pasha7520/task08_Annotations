public class MyClass {

    @MyAnnotation(notNull = true)
    String name;

    @MyAnnotation(value = 7,notNull = true)
    String sername;

    @MyAnnotation(value = 6,notNull = true,unique = true)
    String login;

    @MyAnnotation(notNull = true)
    String password;

    String description;


    public MyClass() {
    }


    public MyClass(String name, String sername, String login, String password, String description) {
        this.name = name;
        this.sername = sername;
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String fullName(String ... args){
        StringBuilder stringBuilder = new StringBuilder();
        for(String s: args){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
    public String[] fullInfo(String ... args){
        return args;
    }
    public boolean lengthMoreThenMust(int size,String ... args){
        int length = 0;
        for(String s : args){
            length += s.length();
        }
        return length > size;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                ", name='" + name + '\'' +
                ", sername='" + sername + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
