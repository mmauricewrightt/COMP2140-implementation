public class Visitor{
    private String fName;
    private String lName;
    private String ID;
    private String resID;

    public Visitor(String fname, String lname, String IDnum, String residentID){
        this.fName = fname;
        this.lName = lname;
        this.ID = IDnum;
        this.resID = residentID;
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

    public String getresID(){
        return resID;
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

    public void setresID(String residentID){
        this.resID = residentID;
    }
}
