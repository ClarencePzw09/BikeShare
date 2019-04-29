package dk.itu.mmad.bikeshare;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "balance")
    private Double balance;

    public User(int id,String name, Double balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Boolean deductBalance(Double deduct){

        if(deduct > balance){
            return false;
        }else{
            System.out.println(balance);
            System.out.println(deduct);
            balance = balance - deduct;
            System.out.println(balance);
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
