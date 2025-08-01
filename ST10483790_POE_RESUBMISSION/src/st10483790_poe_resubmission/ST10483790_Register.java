
package st10483790_poe_resubmission;

import javax.swing.*;

public class ST10483790_Register {

    // variable to set max number of messages
    private static final int MAX_MESSAGES = 100;
    
    //create new user 
    private static ST10483790_Login registeredUser = new ST10483790_Login();
    private static boolean isLoggedIn = false;
    
    
    //  arrays to store different cataegories of messages,message hashes and message ids
    private static ST10483790_Message[] sentMessages = new ST10483790_Message[MAX_MESSAGES];
    private static ST10483790_Message[] storedMessages = new ST10483790_Message[MAX_MESSAGES];
    private static ST10483790_Message[] disregardedMessages = new ST10483790_Message[MAX_MESSAGES];
    private static String[] messageHashes = new String[MAX_MESSAGES];
    private static String[] messageIDs = new String[MAX_MESSAGES];
    
    //counter for each array 
    private static int sentCount = 0, storedCount =0,disregardCount = 0, hashCount = 0, idCount = 0;
    
            
    public static void main(String[] args) {
      //user to register account 
      
        JOptionPane.showMessageDialog(null,"Register Account");
      
        String username = JOptionPane.showInputDialog("Enter Username:\nnote: Username must contain an unsercore and is no more than 10 characters ");
      
        String password = JOptionPane.showInputDialog("Enter Password:\n")
        + ("note: Password must contain: A capital letter,Special character and must have more than 8 characters");
      
        String phone = JOptionPane.showInputDialog("Enter Cell Phone Number(+27...):"
        + "\nnote: CellPhone must start with '+27' followed by the cell phone number");
      
        String fname = JOptionPane.showInputDialog("Enter your first Name:");
      
        String lname = JOptionPane.showInputDialog("Enter your last Name: ");
                
                
      String result = registeredUser.registerUser((username), password, phone, fname, lname);
      JOptionPane.showMessageDialog(null,result);
      if(!result.equals("Registration Successful!")) ;
        
      // User Login 
      String LoginUser = JOptionPane.showInputDialog("Enter username: ");
      String LoginPass = JOptionPane.showInputDialog("Enter Password: ");
      
      if(LoginUser.equals(registeredUser.getUsername()) && LoginPass.equals(registeredUser.getPassword())) {
          isLoggedIn = true;
          JOptionPane.showMessageDialog(null,"Username or Password are incorrect!");
      }
      else {
        JOptionPane.showMessageDialog(null,"Welcome"+fname+" "+lname);
        
    }
      //Main Menu
      while(true){
          String[] menu ={
              "1) Send Message",
                "2) View All Sent Messages",
                "3) View Longest Sent Message",
                "4) Search by Message ID",
                "5) Search by Recipient",
                "6) Delete by Hash",
                "7) Full Report",
                "8) Quit"
            };
              
          String choice = (String) JOptionPane.showInputDialog(null, "What would you like to do ","Menu :",JOptionPane.PLAIN_MESSAGE,null,menu,menu[0]);
          if(choice ==null || choice.startsWith("8")) break;
          
          //Menu choice
          switch (choice.charAt(0)) {
              
                case '1' -> sendMessage();
                case '2' -> viewMessages(sentMessages,sentCount);
                case '3' -> viewLongest();
                case '4' -> searchByID();
                case '5' -> searchByRecipient();
                case '6' -> deleteByHash();
                case '7' -> fullReport();
            }
      }
      JOptionPane.showMessageDialog(null,"QuickChat Ended, GOODBYE :)");
      
    }
      
       // Method to handle sending/storing/disregarding a message
    private static void sendMessage() {
        if (sentCount + storedCount + disregardCount >= MAX_MESSAGES) {
            JOptionPane.showMessageDialog(null, "Message storage is full!");
           
        }

        // Input for recipient and message content
        String recipient = JOptionPane.showInputDialog("Recipient number:");
        String content = JOptionPane.showInputDialog("Message (max 250 chars):");

        if (content == null || content.length() > 250) {
            JOptionPane.showMessageDialog(null, "Message too long.");
            
        }

        // Create message object
        ST10483790_Message msg = new ST10483790_Message(recipient, content);

        // Choose action: Send, Store, Disregard
        String[] options = {"Send", "Store", "Disregard"};
        int action = JOptionPane.showOptionDialog(null, "Choose Action", "Message",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Store message in the appropriate array
        if (action == 0 && sentCount < MAX_MESSAGES) {
            sentMessages[sentCount++] = msg;
        } else if (action == 1 && storedCount < MAX_MESSAGES) {
            storedMessages[storedCount++] = msg;
        } else if (action == 2 && disregardCount < MAX_MESSAGES) {
            disregardedMessages[disregardCount++] = msg;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to store message. Limit reached.");
            
        }

        // Save message hash and ID
        messageHashes[hashCount++] = msg.getMessageHash();
        messageIDs[idCount++] = msg.getMessageID();

        JOptionPane.showMessageDialog(null, "Message action completed:\n" + msg.getDetails());
    }

    // Method to Display all messages 
    private static void viewMessages(ST10483790_Message[] list, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (list[i] != null)
                sb.append(list[i].getDetails()).append("\n\n");
        }
        if (sb.length() == 0) sb.append("No messages.");
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Mehtod to view the longest message
    private static void viewLongest() {
        ST10483790_Message longest = null;
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null &&
                (longest == null || sentMessages[i].getMessageContent().length() > longest.getMessageContent().length())) {
                longest = sentMessages[i];
            }
        }
        JOptionPane.showMessageDialog(null, longest != null ? longest.getDetails() : "No messages.");
    }

    // Method to search messages by ID
    private static void searchByID() {
        String id = JOptionPane.showInputDialog("Enter Message ID:");
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].getMessageID().equals(id)) {
                JOptionPane.showMessageDialog(null, sentMessages[i].getDetails());
               
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    // Search for messages sent to a specific recipient
    private static void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog("Enter recipient:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].getRecipient().equals(recipient)) {
                sb.append(sentMessages[i].getDetails()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No messages to this recipient.");
    }

    // Method to delete message by hash
    private static void deleteByHash() {
        String hash = JOptionPane.showInputDialog("Enter Message Hash:");
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].getMessageHash().equals(hash)) {
                // Shift messages left to remove the one at index i
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                }
                sentMessages[--sentCount] = null; // Clear last slot
                JOptionPane.showMessageDialog(null, "Message deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Hash not found.");
    }

    // Display a full report of all message types
    private static void fullReport() {
        StringBuilder sb = new StringBuilder("SENT:\n");
        for (int i = 0; i < sentCount; i++) sb.append(sentMessages[i].getDetails()).append("\n\n");

        sb.append("STORED:\n");
        for (int i = 0; i < storedCount; i++) sb.append(storedMessages[i].getDetails()).append("\n\n");

        sb.append("DISREGARDED:\n");
        for (int i = 0; i < disregardCount; i++) sb.append(disregardedMessages[i].getDetails()).append("\n\n");

        JOptionPane.showMessageDialog(null, sb.toString());
    }

      
    }
    

