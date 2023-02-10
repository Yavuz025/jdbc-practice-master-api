
package Day6_gson;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Spartan {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private Integer phone;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Spartan() {
    }

    /**
     * 
     * @param gender
     * @param phone
     * @param name
     * @param id
     */
    public Spartan(Integer id, String name, String gender, Integer phone) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPhone(long l) {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

}
