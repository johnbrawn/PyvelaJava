package askdat.pyvela.main;

public class BySubjectContainer {

    public String Name;
    public int Photo;

    public BySubjectContainer(String name, int photo){

        this.Name = name;
        this.Photo = photo;
    }

    public String getName(){ return Name;  }

    public int getPhoto(){ return Photo;  }
}
