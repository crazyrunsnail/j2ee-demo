package personal.davino.j2ee.bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PublisherEntity")
@Table(name = "Publishers", indexes = {
        @Index(name = "Publishers_Names", columnList = "PublisherName")
})
public class Publisher implements Serializable{

    private long id;
    private String name;
    private String address;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "PublisherGenerator")
    @TableGenerator(name = "PublisherGenerator", table = "SurrogateKeys",
            pkColumnName = "TableName", pkColumnValue = "Publishers",
            valueColumnName = "KeyValue", initialValue = 11923, allocationSize = 1)
    @Column(name = "PublisherId")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "PublisherName", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
