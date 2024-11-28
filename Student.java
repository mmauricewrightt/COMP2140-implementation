public class Student{
    private String fName;
    private String lName;
    private String ID;
    private String rmNum;

    public Student(String fname, String lname, String IDnum, String rm){
        this.fName = fname;
        this.lName = lname;
        this.ID = IDnum;
        this.rmNum = rm;
    }

    public String getlName(){
        return lName;
    }

    public String getfName(){
        return fName;
    }
    
    public String getID(){
        return ID;
    }

    public String getRmNum(){
        return rmNum;
    }

    public void setlName(String lName){
        this.lName = lName;
    }

    public void setfName(String fName){
        this.fName = fName;
    }
    
    public void setID(String ID){
        this.ID = ID;
    }

    public void setRmNum(String rmNum){
        this.rmNum = rmNum;
    }
}
