package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "ranks") //, schema = "s225130"
public class Ranks implements Serializable{
    private int id;
    private String name;
    private Integer level;
    private String descripion;
    private Collection<Assignment> assignments;
    private Collection<Users> users;

    public Ranks() {
    }

    public Ranks(String name, Integer level, String descripion) {
        this.name = name;
        this.level = level;
        this.descripion = descripion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 512)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "descripion", nullable = true, length = -1)
    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ranks ranks = (Ranks) o;

        if (id != ranks.id) return false;
        if (name != null ? !name.equals(ranks.name) : ranks.name != null) return false;
        if (level != null ? !level.equals(ranks.level) : ranks.level != null) return false;
        if (descripion != null ? !descripion.equals(ranks.descripion) : ranks.descripion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (descripion != null ? descripion.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rank")
    public Collection<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Collection<Assignment> assignments) {
        this.assignments = assignments;
    }

    @OneToMany(mappedBy = "rank")
    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }
}
