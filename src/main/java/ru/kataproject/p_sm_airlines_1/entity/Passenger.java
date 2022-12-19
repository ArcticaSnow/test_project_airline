package ru.kataproject.p_sm_airlines_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Description of the entity in the PassengerReadme.md
 */

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "passengers")
@ToString
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate dateOfBirth;

    @Email
    @Column(name = "email")
    private String username;

    @Column
    private String password;

    @Column(name = "mobile_num")
    private String mobileNumber;

    @Column(name = "nick_name")
    private String nickName;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Document> documents = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(getId(), passenger.getId())
                && Objects.equals(getFirstName(), passenger.getFirstName())
                && Objects.equals(getMiddleName(), passenger.getMiddleName())
                && Objects.equals(getLastName(), passenger.getLastName())
                && Objects.equals(getDateOfBirth(), passenger.getDateOfBirth())
                && Objects.equals(getUsername(), passenger.getUsername())
                && Objects.equals(getPassword(), passenger.getPassword())
                && Objects.equals(getMobileNumber(), passenger.getMobileNumber())
                && Objects.equals(getNickName(), passenger.getNickName())
                && getDocuments().containsAll(passenger.getDocuments())
                && passenger.getDocuments().containsAll(getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getMiddleName(), getLastName(),
                getDateOfBirth(), getUsername(), getPassword(), getMobileNumber(),
                getNickName(), getDocuments());
    }
}
