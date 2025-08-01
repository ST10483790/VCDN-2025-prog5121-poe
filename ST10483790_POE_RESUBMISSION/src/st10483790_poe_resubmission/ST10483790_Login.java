
package st10483790_poe_resubmission;


public class ST10483790_Login {
    

    
    
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    
    
   //method to validate username
    public boolean checkUserName(String username){
       
        return username.contains("_")&&username.length()<=10;
    }
          
    
    
    //method to validate password 
    public boolean checkPasswordComplexity(String password){
      
        return password.length()>=8
                && password.matches(".*[A-Z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
  
    }
    
    
    
    //method to validate that the cellphone number is correctly formatted
    public boolean checkCellPhoneNumber(String phoneNumber){
        
        return phoneNumber.matches("\\+27\\d{9}$");
    }
    
    // method to register user if data is valid
    public String registerUser(String username,String password,String cellPhoneNumber,String firstName,String lastName){
        
        if(!checkUserName(username)){
            return "The username is incorrectly formated\nUser name must contain an underscore and is less than 10 characters";
        }
        if(!checkPasswordComplexity(password)){
            return"The password does not meet requirements \nPassword must have a capital leteer,a didgit, special character and is more than 8 characters";
        }
        if(!checkCellPhoneNumber(cellPhoneNumber)){
            return "The cell phone is incorrectly formated\nMust start with'+27'";
        }
        
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "Registration Successful ";
               
        }

    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
}
