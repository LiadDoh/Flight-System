package Models;

public class Countries implements POCO{

    public int id;
    public String name;
    public byte[] flag;

    public Countries(int id, String name, byte[] flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

}
