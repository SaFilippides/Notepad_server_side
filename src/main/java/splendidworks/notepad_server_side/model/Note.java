/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.model;

/**
 *
 * @author filip
 */
public class Note {

    private Integer id;
    private String name;
    private String image_path;
    private String note;

    public Note() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Note(Integer id) {
        super();
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return image_path;
    }

    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", name=" + name + ", image_path=" + image_path + ", note=" + note + '}';
    }

}
