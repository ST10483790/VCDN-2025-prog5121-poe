
package st10483790_poe_resubmission;

import java.util.Random;

public class ST10483790_Message {
    
    // how many messages have been created
    private static int messageCount = 0;
    
    // Message properties
    private final String messageID;
    private final String recipient;
    private final String messageContent;
    private final String messageHash;
    
    
    //Constructor
    public ST10483790_Message(String recipient, String content)  {
        messageCount++ ;
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageContent = content;
        this.messageHash = generateHash(content);
        
    }
    
    
    
    private String generateMessageID() {
        return String.format("08d", new Random().nextInt(100_000_000));
    }
    
    //return content to uppercase
    public static String generateHash(String content){
        return "00:0:" + content.toUpperCase();
    }
    
    
    //Message lenght is less than 250 characters
    public static String validateMessageLength(String content){
        if(content.length()<= 250){
            return "Message ready to send.";
        }
        int excess = content.length() - 250;
        return "Message is longer than 250 characters by: "+excess;
   
    }
    
    //Validates that phone number starts with +27 
    public static String validateCellNumber(String number){
        if(number.matches("\\+\\d{10,15}")){
            return "Cell phone number successfully captured.";
        }
        return"Cell phone number does not start with +27 or number is too long or too short";
    }

    //Menu for message
    public static String handleUserSelection(int option){
        switch(option){
            case 1: return "Message successfully sent";
            case 2: return "Press 0 to delete message";
            case 3: return "Message successfully stored";
            default : return "Invallid selection.";
        }
    }
    
    //Getters for fields
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public String getMessageHash() {return messageHash; }
   public static int getMessageCount() { return messageCount; }
    
    
    
    public String getDisplayMessageID() {
        return "Message ID generated: "+messageID;
    }
    
    public String getDetails(){
        return "Message ID: "+messageID +
        "\nRecipient: " + recipient +
        "\nContent: " + messageContent +
        "\nMessage Hash: " + messageHash;        
    }
    
   
}