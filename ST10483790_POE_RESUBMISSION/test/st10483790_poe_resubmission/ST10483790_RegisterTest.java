
package st10483790_poe_resubmission;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ST10483790_RegisterTest {
    //Array to simulate the sent messages
    private ST10483790_Message[] testMessages;

    //Setup method to initialize test messages before each test
    @BeforeEach
    public void setup() {
        testMessages = new ST10483790_Message[4];
        testMessages[0] = new ST10483790_Message("+27830000001", "Did you get the cake?");
        testMessages[1] = new ST10483790_Message("+27830000002", "It is dinner time!");
        testMessages[2] = new ST10483790_Message("+27830000003", "Where are you? You are late! I have asked you to be on time.");
        testMessages[3] = new ST10483790_Message("+27838884567", "Ok, I am leaving without you.");
    }

    //Test to confirm the messages array is populated with expected content
    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals("Did you get the cake?", testMessages[0].getMessageContent());
        assertEquals("It is dinner time!", testMessages[1].getMessageContent());
    }

    //Test to find and validate the longest message from the list
    @Test
    public void testDisplayLongestMessage() {
        ST10483790_Message longest = testMessages[0];
        for (ST10483790_Message msg : testMessages) {
            if (msg.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = msg;
            }
        }

        assertEquals("Where are you? You are late! I have asked you to be on time.",
                     longest.getMessageContent());
    }

    //Test to find a message by its unique ID (simulating search by message ID)
    @Test
    public void testSearchForMessageID() {
        String targetID = testMessages[3].getMessageID();  // Simulate known ID for message 4
        ST10483790_Message found = null;

        for (ST10483790_Message msg : testMessages) {
            if (msg.getMessageID().equals(targetID)) {
                found = msg;
                break;
            }
        }

        //Check if message was found and matches expected recipient
        assertNotNull(found);
        assertEquals("+27838884567", found.getRecipient());
    }

    //Test to retrieve all messages sent to a specific recipient
    @Test
    public void testSearchByRecipient() {
        String recipient = "+27838884567";
        StringBuilder result = new StringBuilder();

        for (ST10483790_Message msg : testMessages) {
            if (msg.getRecipient().equals(recipient)) {
                result.append(msg.getMessageContent()).append("\n");
            }
        }

        //Confirm message content was correctly retrieved
        assertTrue(result.toString().contains("Ok, I am leaving without you."));
    }

    //Test to simulate deleting a message by its hash
    @Test
    public void testDeleteMessageUsingHash() {
        String hashToDelete = testMessages[2].getMessageHash();  // Target the long message
        boolean deleted = false;

        //Simulate deletion by setting the array slot to null
        for (int i = 0; i < testMessages.length; i++) {
            if (testMessages[i] != null && testMessages[i].getMessageHash().equals(hashToDelete)) {
                testMessages[i] = null;
                deleted = true;
                break;
            }
        }

        //Ensure message was successfully deleted
        assertTrue(deleted, "Message with specified hash should be deleted");
    }

    //Test to display a formatted report containing hash, recipient, and message content
    @Test
    public void testDisplayReport() {
        StringBuilder report = new StringBuilder();

        for (ST10483790_Message msg : testMessages) {
            if (msg != null) {
                report.append("Hash: ").append(msg.getMessageHash()).append("\n");
                report.append("Recipient: ").append(msg.getRecipient()).append("\n");
                report.append("Message: ").append(msg.getMessageContent()).append("\n\n");
            }
        }

        //Check that the report includes expected data
        String reportStr = report.toString();
        assertTrue(reportStr.contains("Recipient: +27830000001"));
        assertTrue(reportStr.contains("Message: It is dinner time!"));
    }
}
