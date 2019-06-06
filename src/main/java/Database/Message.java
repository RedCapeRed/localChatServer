package Database;

import javax.persistence.*;

@Entity
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    String nameSender;
    @Column
    String nameReceiver;
    @Column
    String messageText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Message() {
    }
    public Message(String nameSender, String nameReceiver, String messageText) {
        this.nameSender = nameSender;
        this.nameReceiver = nameReceiver;
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNameSender() {
        return nameSender;
    }
    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }
    public String getNameReceiver() {
        return nameReceiver;
    }
    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getNameSender() + " --> " + getNameReceiver() + " : " + getMessageText();
    }
}
