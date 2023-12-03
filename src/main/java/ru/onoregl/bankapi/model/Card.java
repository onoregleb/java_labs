package ru.onoregl.bankapi.model;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cards")
//@Builder(setterPrefix = "with")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cardId")
    private  String cardId ;
    @Column(name = "accountid")
    private String accountid ;
    @Column(name = "userid")
    private  String userid ;

    public String getId() {
        return cardId;
    }

    public void setId(String id) {
        this.cardId = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
